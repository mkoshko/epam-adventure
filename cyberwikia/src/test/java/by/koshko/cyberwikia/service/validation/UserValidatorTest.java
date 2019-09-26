package by.koshko.cyberwikia.service.validation;

import by.koshko.cyberwikia.service.ServiceException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Properties;

import static org.testng.Assert.assertTrue;

public class UserValidatorTest {

    @BeforeTest
    public void setUp() throws ServiceException {
        ValidationProperties.getInstance().init();
    }

    @Test
    public void testTest1() {
        Properties prop = new Properties();
        prop.put("login", "43837");
        prop.put("email", "kamfkm@mdg.com");
        prop.put("role", "2");
        assertTrue(UserValidator.test(prop));
    }
}