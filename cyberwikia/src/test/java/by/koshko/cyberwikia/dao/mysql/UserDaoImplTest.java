package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.UserDao;
import by.koshko.cyberwikia.dao.cyberpool.ConnectionPool;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.testng.Assert.*;

public class UserDaoImplTest {

    private UserDao userDao;
    private Connection connection;

    @BeforeTest
    public void setUp() throws SQLException, DaoException {
        userDao = new UserDaoImpl();
        ConnectionPool.access().init();
        connection = ConnectionPool.access().getConnection();
        userDao.setConnection(connection);
    }

    @AfterTest
    public void tearDown() throws SQLException, DaoException {
        User user = userDao.findByLogin("testUser3");
        userDao.delete(user);
        connection.close();
    }

    @Test
    public void testFindByLogin() throws DaoException {
        String userLogin = "user1";
        User user = userDao.findByLogin(userLogin);
        assertEquals(user.getLogin(), userLogin);
    }

    @Test
    public void testFindByLoginEmpty() throws DaoException {
        String userLogin = "";
        User user = userDao.findByLogin(userLogin);
        assertNull(user);
    }

    @Test
    public void testFindByLoginNull() throws DaoException {
        User user = userDao.findByLogin(null);
        assertNull(user);
    }

    @Test
    public void testGet() throws DaoException {
        User user = userDao.get(1);
        assertEquals(user.getId(), 1);
    }

    @Test
    public void testGetNull() throws DaoException {
        Long id = null;
        User user = userDao.get(id);
        assertEquals(user.getId(), 1);
    }

    @Test
    public void testGetWrongIndex() throws DaoException {
        User user = userDao.get(0);
        assertNull(user);
    }

    @Test
    public void testGetAll() throws DaoException {
        List<User> users = userDao.getAll();
        assertFalse(users.isEmpty());
    }

    @Test(dependsOnMethods = {"testFindByLogin"})
    public void testSave() throws DaoException {
        User user = new User();
        user.setLogin("testUser");
        user.setEmail("testuser@gmail.com");
        user.setPassword("uZCjA^P4%L&+.cNb");
        user.setRole(1);
        userDao.save(user);
        User user1 = userDao.findByLogin("testUser");
        user.setId(user1.getId());
        assertEquals(user1, user);
    }

    /**
     * Attempt to save user without required parameters.
     *
     * @throws DaoException if user cannot be saved.
     */
    @Test(expectedExceptions = DaoException.class)
    public void testSaveFail() throws DaoException {
        User user = new User();
        user.setLogin("testUser");
        user.setEmail("mail@email.com");
        user.setPassword("uZCjA^P4%L&+.cNb");
        userDao.save(user);
    }

    @Test(dependsOnMethods = {"testSave", "testFindByLogin"})
    public void testUpdate() throws DaoException {
        User user = new User();
        user.setLogin("testUser2");
        user.setEmail("mail@gmail.com");
        user.setRole(1);
        user.setPassword("password");
        userDao.save(user);
        user.setLogin("testUser3");
        user.setEmail("mail2@gmail.com");
        user.setRole(2);
        user.setPassword("password2");
        User user1 = userDao.findByLogin("testUser2");
        user.setId(user1.getId());
        userDao.update(user);
        user1 = userDao.findByLogin("testUser3");
        assertEquals(user1, user);
    }

    @Test(dependsOnMethods = "testSave")
    public void testDelete() throws DaoException {
        User user = userDao.findByLogin("testUser");
        userDao.delete(user);
        user = userDao.findByLogin("testUser");
        assertNull(user);
    }
}