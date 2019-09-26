package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.cyberpool.ConnectionPool;
import by.koshko.cyberwikia.service.impl.UserServiceImpl;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserServiceImplTest {

    @BeforeTest
    public void init() throws DaoException {
        ConnectionPool.getInstance().init();
    }

    @Test
    public void testSignIn() throws ServiceException {
        UserService userService = new UserServiceImpl();
        User user = userService.signIn("user1", "user1");
        assertNotNull(user);
    }
}