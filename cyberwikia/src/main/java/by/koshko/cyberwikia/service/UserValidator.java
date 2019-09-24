package by.koshko.cyberwikia.service;

import java.util.Properties;
import java.util.function.Predicate;

public class UserValidator implements Predicate<Properties> {

    @Override
    public boolean test(final Properties properties) {
        String login = properties.getProperty("login");
        String email = properties.getProperty("email");
        String password = properties.getProperty("password");
        if (login == null || login.isEmpty()) {
            return false;
        }
        if (email == null || email.isEmpty() || email.length() < 3 || email.length() > 254) {
            return false;
        }
        if (password == null || password.isEmpty()) {
            return false;
        }
        return true;
    }
}
