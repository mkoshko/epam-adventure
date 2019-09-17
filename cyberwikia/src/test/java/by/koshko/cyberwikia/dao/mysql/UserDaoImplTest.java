package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.ConnectorDB;
import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.UserDao;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class UserDaoImplTest {

    @Test
    public void testFindByLogin() throws SQLException, DaoException {
        Connection connection = ConnectorDB.getConnection();
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setConnection(connection);
        Optional<User> user = userDao.findByLogin("user1");
        connection.close();
        user.ifPresent(System.out::println);
    }

    @Test
    public void testGet() throws SQLException, DaoException {
        Connection connection = ConnectorDB.getConnection();
        UserDao userDao = new UserDaoImpl();
        userDao.setConnection(connection);
        Optional<User> user = userDao.get(2);
        user.ifPresent(System.out::println);
    }

    @Test
    public void testGetAll() throws SQLException, DaoException {
        Connection connection = ConnectorDB.getConnection();
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setConnection(connection);
        List users = userDao.getAll();
        System.out.println(users);
    }
}