package by.koshko.cyberwikia.service.validation;

import by.koshko.cyberwikia.bean.Role;

import java.util.Map;
import java.util.Properties;

public class UserValidator {
    private UserValidator() {
    }

    public static boolean test(final Properties properties) {
        String emailRegex = "^[\\w-_.+]*[\\w-_.]@([\\w]+.)+[\\w]+[\\w]$";
        Map<String, Integer> validValues = ValidationProperties
                .getInstance().getPermissibleValues();
        int maxLoginLength = validValues.get("login-max-length");
        int maxEmailLength = validValues.get("email-max-length");
        int minEmailLength = validValues.get("email-min-length");
        String login = properties.getProperty("login");
        String email = properties.getProperty("email");
        String role = properties.getProperty("role");
        if (login == null
            || login.isEmpty()
            || login.length() > maxLoginLength) {
            return false;
        }
        if (email == null
            || email.isEmpty()
            || email.length() < minEmailLength
            || email.length() > maxEmailLength
            || !email.matches(emailRegex)) {
            return false;
        }
        try {
            int role0 = Integer.parseInt(role);
            if (role0 == 0 || role0 > Role.values().length) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
