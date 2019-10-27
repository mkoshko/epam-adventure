package by.koshko.cyberwikia.dao.pool;

import by.koshko.cyberwikia.dao.DaoException;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPool {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger(getClass());
    /**
     * Connection pool instance.
     */
    private static final ConnectionPool INSTANCE = new ConnectionPool();
    /**
     * Collection with free connections.
     */
    private ConcurrentLinkedQueue<ConnectionWrapper> pool
            = new ConcurrentLinkedQueue<>();
    /**
     * Collection with used connections.
     */
    private ConcurrentSkipListSet<ConnectionWrapper> used
            = new ConcurrentSkipListSet<>();
    /**
     * Locker.
     */
    private ReentrantLock locker = new ReentrantLock();
    /**
     * A JNDI DataSource for a Mysql JDBC connection.
     */
    private MysqlDataSource source;
    /**
     * Maximum number of connections.
     */
    private int maxPoolSize;
    /**
     * Number of created connections.
     */
    private AtomicInteger connectionNumber;
    /**
     * The time in seconds to wait for the database operation
     * used to validate the connection to complete.
     */
    private int checkConnectionTimeout;

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    /**
     * Initialize connection pool.
     *
     * @param bundleName Bundle name which contains parameters for connection
     *                   pool initialization.
     * @throws DaoException throws if some errors occurred while initialization.
     */
    public void init(final String bundleName) throws DaoException {
        try {
            logger.info("Initializing connection pool.");
            ResourceBundle bundle = ResourceBundle.getBundle(bundleName);
            String driver = bundle.getString("db.driver");
            String url = bundle.getString("db.url");
            String user = bundle.getString("db.user");
            String pass = bundle.getString("db.pass");
            Class.forName(driver);
            int initPoolSize = Integer
                    .parseInt(bundle.getString("db.init-pool-size"));
            maxPoolSize = Integer
                    .parseInt(bundle.getString("db.max-pool-size"));
            checkConnectionTimeout = Integer
                    .parseInt(bundle.getString("db.connection-timeout"));
            if (maxPoolSize < 1
                || initPoolSize < 1
                || initPoolSize > maxPoolSize) {
                throw new DaoException("Invalid parameters"
                                       + " for pool initialization");
            }
            source = new MysqlDataSource();
            source.setURL(url);
            source.setUser(user);
            source.setPassword(pass);
            for (int i = 0; i < initPoolSize; i++) {
                pool.add(new ConnectionWrapper(source.getConnection()));
            }
            connectionNumber = new AtomicInteger(initPoolSize);
            logger.info("Connection pool is successfully initialized."
                        + " Idle connections: {}", pool.size());
        } catch (SQLException | MissingResourceException
                | ClassNotFoundException | NumberFormatException e) {
            throw new DaoException("Cannot initialize connection pool.", e);
        }
    }

    /**
     * Returns connection if available, or create new if
     * {@link #connectionNumber} less then {@link #maxPoolSize}.
     *
     * @return {@code Connection} if available.
     * @throws DaoException if no available connection and connection cannot be
     *                      created due to max pool size is reached.
     */
    public ConnectionWrapper getConnection() throws DaoException {
        try {
            locker.lock();
            if (!pool.isEmpty()) {
                ConnectionWrapper connection;
                connection = pool.poll();
                used.add(connection);
                logger.debug("Got a connection from pool. "
                             + "Idle connections: {}, used connections: {}",
                        pool.size(), used.size());
                return connection;
            } else {
                if (connectionNumber.get() < maxPoolSize) {
                    return createConnection();
                }
                throw new DaoException("No available connections.");
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot create new connection.", e);
        } finally {
            locker.unlock();
        }
    }

    /**
     * Returns connection to connection pool. Removes given connection
     * from {@link #used}, and put it into {@link #pool}.
     *
     * @param connection {@code Connection} to be released.
     */
    public void releaseConnection(final ConnectionWrapper connection) {
        try {
            if (connection.isValid(checkConnectionTimeout)) {
                connection.clearWarnings();
                used.remove(connection);
                pool.add(connection);
                logger.debug("Connection released. "
                             + "Idle connections: {}, used connections: {}",
                        pool.size(), used.size());
            }
        } catch (SQLException e) {
            logger.error("Cannot release connection. Connection is invalid. "
                         + "SQL state: {}, SQL message: {}",
                    e.getSQLState(), e.getMessage());
            try {
                connection.getConnection().close();
            } catch (SQLException e1) {
                logger.error("Cannot close connection. "
                             + "SQL state: {}, SQL message: {}.",
                        e.getSQLState(), e.getMessage());
            }
        }
    }

    /**
     * Closes all created connections.
     */
    public void close() {
        pool.addAll(used);
        used.clear();
        for (ConnectionWrapper con : pool) {
            try {
                con.getConnection().close();
            } catch (SQLException e) {
                logger.error("Cannot close connection. "
                             + "SQL state: {}, SQL message: {}.",
                        e.getSQLState(), e.getMessage());
            }
        }
        pool.clear();
    }

    /**
     * Creates and return new connection.
     *
     * @return new connection.
     * @throws SQLException throws if connection cannot be created.
     */
    private ConnectionWrapper createConnection() throws SQLException {
        logger.debug("Creating a connection.");
        ConnectionWrapper connection
                = new ConnectionWrapper(source.getConnection());
        used.add(connection);
        logger.info("New connection is created. "
                    + "Idle connections: {}, used connections: {}",
                pool.size(), used.size());
        connectionNumber.incrementAndGet();
        return connection;
    }
}
