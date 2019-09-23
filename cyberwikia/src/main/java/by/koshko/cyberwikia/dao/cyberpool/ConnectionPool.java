package by.koshko.cyberwikia.dao.cyberpool;

import by.koshko.cyberwikia.dao.DaoException;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final ConnectionPool INSTANCE = new ConnectionPool();
    private static Logger logger = LogManager.getLogger(ConnectionPool.class);
    private LinkedList<ConnectionWrapper> pool
            = new LinkedList<>();
    private TreeSet<ConnectionWrapper> used
            = new TreeSet<>();
    private ReentrantLock locker = new ReentrantLock();
    private MysqlDataSource source = new MysqlDataSource();
    private int maxPoolSize;
    private int connectionNumber;
    private int checkConnectionTimeout;

    private ConnectionPool() {
    }

    public static ConnectionPool access() {
        return INSTANCE;
    }

    public void init() throws DaoException {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("database");
            String url = bundle.getString("db.url");
            String user = bundle.getString("db.user");
            String pass = bundle.getString("db.pass");
            int initPoolSize = Integer.parseInt(bundle.getString("db.init-pool-size"));
            maxPoolSize = Integer.parseInt(bundle.getString("db.max-pool-size"));
            checkConnectionTimeout = Integer.parseInt(bundle.getString("db.connection-timeout"));
            source.setURL(url);
            source.setUser(user);
            source.setPassword(pass);
            for (int i = 0; i < initPoolSize; i++) {
                pool.add(new ConnectionWrapper(source.getConnection()));
            }
            connectionNumber = initPoolSize;
        } catch (SQLException e) {
            logger.error("Initialization error. {}", e.getMessage());
            throw new DaoException("Cannot initialize connection pool.");
        }
    }

    public ConnectionWrapper getConnection() throws DaoException {
        try {
            locker.lock();
            if (!pool.isEmpty()) {
                ConnectionWrapper connection = null;
                connection = pool.pop();
                used.add(connection);
                logger.info("Got a connection from pool. Idle connections: {}, used connections: {}",
                        pool.size(), used.size());
                return connection;
            } else {
                if (connectionNumber < maxPoolSize) {
                    return createConnection();
                }
                throw new DaoException("No available connections.");
            }
        } catch (SQLException e) {
            logger.error("Cannot create new connection. SQL state: {}, SQL message: {}.",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot create new connection.");
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
                logger.info("Connection released. Idle connections: {}, used connections: {}",
                        pool.size(), used.size());
            }
        } catch (SQLException e) {
            logger.error("Cannot release connection. Connection is invalid. "
                         + "SQL state: {}, SQL message: {}", e.getSQLState(), e.getMessage());
            try {
                connection.getConnection().close();
            } catch (SQLException e1) {
                logger.error("Cannot close connection. SQL state: {}, SQL message: {}.",
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
                logger.error("Cannot close connection. SQL state: {}, SQL message: {}.",
                        e.getSQLState(), e.getMessage());
            }
        }
    }

    private ConnectionWrapper createConnection() throws SQLException {
        ConnectionWrapper connection = new ConnectionWrapper(source.getConnection());
        used.add(connection);
        logger.info("New connection is created. Idle connections: {}, used connections: {}",
                pool.size(), used.size());
        connectionNumber++;
        return connection;
    }
}
