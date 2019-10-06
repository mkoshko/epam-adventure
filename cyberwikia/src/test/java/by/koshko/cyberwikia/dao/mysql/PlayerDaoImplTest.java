package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoFactory;
import by.koshko.cyberwikia.dao.PlayerDao;
import by.koshko.cyberwikia.dao.cyberpool.ConnectionPool;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PlayerDaoImplTest {

    @Test
    public void testGetRowsNumber() throws DaoException {
        ConnectionPool.getInstance().init();
        PlayerDao playerDao = DaoFactory.getPlayerDao();
        playerDao.setConnection(ConnectionPool.getInstance().getConnection());
        System.out.println(playerDao.getRowsNumber());
        ConnectionPool.getInstance().close();
    }
}