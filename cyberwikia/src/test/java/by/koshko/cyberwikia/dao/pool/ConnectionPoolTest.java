package by.koshko.cyberwikia.dao.pool;

import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.service.impl.AbstractServiceTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConnectionPoolTest extends AbstractServiceTest {

    private int maxPoolSize;

    @BeforeClass
    public void initPoolSize() {
        ResourceBundle bundle = ResourceBundle.getBundle("database");
        maxPoolSize = Integer.parseInt(bundle.getString("db.max-pool-size"));
    }

    @BeforeMethod
    public void initPool() throws DaoException {
        ConnectionPool.getInstance().close();
        ConnectionPool.getInstance().init("database");
    }

    @Test
    public void testInit() {
    }

    /**
     * Attempt to get more connections then max pool size.
     *
     * @throws DaoException if cannot get a new connection.
     */
    @Test(expectedExceptions = DaoException.class)
    public void testGetConnectionFail() throws DaoException {
        for (int i = 0; i < maxPoolSize + 1; i++) {
            ConnectionPool.getInstance().getConnection();
        }
    }

    @Test
    public void testGetConnectionSuccess() throws DaoException {
        for (int i = 0; i < maxPoolSize; i++) {
            ConnectionPool.getInstance().getConnection();
        }
    }

    @Test
    public void testReleaseConnection() throws DaoException {
        List<ConnectionWrapper> connections = new ArrayList<>();
        for (int i = 0; i < maxPoolSize; i++) {
            connections.add(ConnectionPool.getInstance().getConnection());
        }
        for (ConnectionWrapper connection : connections) {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Test
    public void testClose() {
        ConnectionPool.getInstance().close();
    }
}