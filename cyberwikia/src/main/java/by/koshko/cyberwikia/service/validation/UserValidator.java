package by.koshko.cyberwikia.service.validation;

import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class UserValidator implements Validator {

    private static Logger logger = LogManager.getLogger(UserValidator.class);

    private static final String LOGIN_MAX_LENGTH = "user.login";
    private static final String LOGIN_REGEX = "user.login-regex";
    private static final String EMAIL_MAX_LENGTH = "user.email-max";
    private static final String EMAIL_MIN_LENGTH = "user.email-min";
    private static final String EMAIL_REGEX = "user.email-regex";
    private static final String PASSWORD_MIN_LENGTH = "user.password-min";

    private int loginMaxLength;
    private int passwordMinLength;
    private int emailMinLength;
    private int emailMaxLength;
    private String loginRegex;
    private String emailRegex;


    public void init() throws ServiceException {
        Map<String, String> values = ValidationPropertiesLoader.getValues();
        loginMaxLength = Integer.parseInt(values.get(LOGIN_MAX_LENGTH));
        passwordMinLength = Integer.parseInt(values.get(PASSWORD_MIN_LENGTH));
        emailMinLength = Integer.parseInt(values.get(EMAIL_MIN_LENGTH));
        emailMaxLength = Integer.parseInt(values.get(EMAIL_MAX_LENGTH));
        loginRegex = values.get(LOGIN_REGEX);
        emailRegex = values.get(EMAIL_REGEX);
    }

    public boolean test(final User user, final boolean checkPassword) {
        if (user == null) {
            return false;
        }
        String login = user.getLogin();
        String password = user.getPassword();
        String email = user.getEmail();
        if (login == null
            || login.isEmpty()
            || login.length() >= loginMaxLength
            || !login.matches(loginRegex)) {
            logger.debug("Invalid user login.");
            return false;
        }
        if (email == null
            || email.length() < emailMinLength
            || email.length() > emailMaxLength
            || !email.matches(emailRegex)) {
            logger.debug("Invalid user email.");
            return false;
        }
        if (checkPassword && (password == null
                              || password.length() < passwordMinLength)) {
            logger.debug("Invalid user password. Must have at least"
                         + " {} characters.", passwordMinLength);
            return false;
        }
        return true;
    }
}
