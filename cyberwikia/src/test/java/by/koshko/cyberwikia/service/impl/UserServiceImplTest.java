package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.dao.UserDao;
import by.koshko.cyberwikia.dao.mysql.TransactionImpl;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.UserService;
import com.wix.mysql.SqlScriptSource;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.wix.mysql.ScriptResolver.classPathScript;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class UserServiceImplTest extends AbstractServiceTest {

    private ServiceFactory factory;
    private UserService userService;
    private static final SqlScriptSource[] SQL_SCRIPT_SOURCES
            = {classPathScript("sql/fill_user.sql")};

    @BeforeClass
    public void setUp() throws ServiceException {
        factory = new ServiceFactory();
        userService = factory.getUserService();
        database.executeScripts(DATABASE, SQL_SCRIPT_SOURCES);
    }

    @AfterClass
    public void tearDown() {
        factory.close();
    }

    @DataProvider(name = "correct_user_signIn")
    private Object[][] provide() {
        return new Object[][]{
                {"user0", "user0"},
                {"user1", "user1"},
        };
    }

    @DataProvider(name = "id_provider")
    private Object[][] provide2() {
        return new Object[][]{
                {1}, {2}, {3}, {4}, {5}
        };
    }

    @DataProvider(name = "incorrect_user_signIn")
    private Object[][] provide3() {
        return new Object[][]{
                {"", "password"},
                {null, "password"},
                {"user1", ""},
                {"user1", null},
                {null, null},
                {"", ""},
        };
    }

    @DataProvider(name = "correct_user_signUp")
    private Object[][] provide4() {
        return new Object[][]{
                {"login", "login@email.com", "password"},
                {"login1", "a@b.by", "MGle5%#35jLM3m"},
        };
    }

    @DataProvider(name = "incorrect_user_signUp")
    private Object[][] provide5() {
        return new Object[][] {
                {"", "login@email.com", "password"},
                {"login5", "", "password"},
                {"login5", "login@email.com", ""},
                {null, "login@email.com", "password"},
                {"login5", null, "password"},
                {"login5", "login@email.com", null},
                {"login5", "login@email", "password"},
                {"login5", "loginemail.com", "password"},
                {"login5", "@email.com", "password"},
                {null, null, null}
        };
    }

    @DataProvider(name = "incorrect_password")
    private Object[][] provide6() {
        return new Object[][]{
                {""},
                {"sdgds4"},
                {null},
        };
    }

    @Test(dataProvider = "correct_user_signIn")
    public void testSignIn(final String login, final String password)
            throws ServiceException {
        User user = userService.signIn(login, password);
        assertNotNull(user);
    }

    @Test(dataProvider = "incorrect_user_signIn")
    public void testSignIn2(final String login, final String password)
            throws ServiceException {
        User user = userService.signIn(login, password);
        assertNull(user);
    }

    @Test(dataProvider = "correct_user_signUp")
    public void testSighUp(final String login, final String email,
                           final String password)
            throws DaoException, ServiceException {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        userService.sighUp(user);
        Transaction transaction = new TransactionImpl();
        UserDao userDao = transaction.getUserDao();
        User newUser = userDao.findByLogin(login);
        transaction.close();
        assertNotNull(newUser);
    }

    @Test(dataProvider = "incorrect_user_signUp")
    public void testSighUp2(final String login, final String email,
                           final String password)
            throws DaoException, ServiceException {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        userService.sighUp(user);
        Transaction transaction = new TransactionImpl();
        UserDao userDao = transaction.getUserDao();
        User newUser = userDao.findByLogin(login);
        transaction.close();
        assertNull(newUser);
    }

    @Test(dataProvider = "id_provider")
    public void testGet(final long id) throws ServiceException {
        User user = userService.get(id);
        assertEquals(user.getId(), id);
    }

    @Test(dependsOnMethods = {"testSignIn"})
    public void testUpdatePassword() throws ServiceException {
        String password = "skjnkewmyw#4KLmy";
        userService.updatePassword(1, "user0", password);
        User user = userService.signIn("user0", password);
        assertNotNull(user);
    }

    @Test(dependsOnMethods = {"testSignIn"},
            dataProvider = "incorrect_password")
    public void testUpdatePassword2(final String password)
            throws ServiceException {
        userService.updatePassword(1, "user0", password);
        User user = userService.signIn("user0", password);
        assertNull(user);
    }
}