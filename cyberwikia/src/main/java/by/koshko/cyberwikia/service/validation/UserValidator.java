package by.koshko.cyberwikia.service.validation;

import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ValidationPropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class UserValidator {

    private static Logger logger = LogManager.getLogger(UserValidator.class);
    private static final String LOGIN_LENGTH = "user.login";
    private static final String LOGIN_REGEX = "user.login-regex";
    private static final String EMAIL_MAX_LENGTH = "user.email-max";
    private static final String EMAIL_MIN_LENGTH = "user.email-min";
    private static final String EMAIL_REGEX = "user.email-regex";
    private static final String PASSWORD_MIN_LENGTH = "user.password-min";

    public static boolean test(final User user, final boolean checkPassword)
            throws ServiceException {
        try {
            if (user == null) {
                return false;
            }
            Map<String, String> values = ValidationPropertiesLoader.getValues();
            String login = user.getLogin();
            String password = user.getPassword();
            String email = user.getEmail();

            int loginMaxLength = Integer.parseInt(values.get(LOGIN_LENGTH));
            int passwordMinLength = Integer.parseInt(values.get(PASSWORD_MIN_LENGTH));
            int emailMinLength = Integer.parseInt(values.get(EMAIL_MIN_LENGTH));
            int emailMaxLength = Integer.parseInt(values.get(EMAIL_MAX_LENGTH));

            String loginRegex = values.get(LOGIN_REGEX);
            String emailRegex = values.get(EMAIL_REGEX);

            if (login == null
                || login.isEmpty()
                || login.length() >= loginMaxLength
                || !login.matches(loginRegex)) {
                return false;
            }
            if (email == null
                || email.length() < emailMinLength
                || email.length() > emailMaxLength
                || !email.matches(emailRegex)) {
                return false;
            }
            if (checkPassword) {
                if (password == null
                    || password.length() < passwordMinLength) {
                    return false;
                }
            }
        } catch (ServiceException | NumberFormatException e) {
            throw new ServiceException(e.getMessage());
        }
        return true;
    }
}
