package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.User;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserServiceImplTest {

    @Test
    public void testSignIn() throws ServiceException {
        UserService userService = new UserServiceImpl();
        User user = userService.signIn("user1", "user1");
        assertNotNull(user);
    }
}