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
    private static final ConnectionPool INSTANCE = new ConnectionPool();
    private Logger logger = LogManager.getLogger(getClass());
    private ConcurrentLinkedQueue<ConnectionWrapper> pool
            = new ConcurrentLinkedQueue<>();
    private ConcurrentSkipListSet<ConnectionWrapper> used
            = new ConcurrentSkipListSet<>();
    private ReentrantLock locker = new ReentrantLock();
    private MysqlDataSource source;
    private int maxPoolSize;
    private AtomicInteger connectionNumber;
    private int checkConnectionTimeout;

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

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
    }

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
