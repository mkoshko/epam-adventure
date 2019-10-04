package by.koshko.cyberwikia.service.validation;

import by.koshko.cyberwikia.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class ValidationPropertiesLoader {

    private ValidationPropertiesLoader() {
    }

    private static Logger logger = LogManager.getLogger(ValidationPropertiesLoader.class);
    private static Map<String, String> values = new HashMap<>();
    private static boolean isLoad = false;
    private static List<Validator> validatorList = new ArrayList<>();
    static {
        validatorList.add(ValidationFactory.getUserValidator());
        validatorList.add(ValidationFactory.getTeamValidator());
        validatorList.add(ValidationFactory.getTournamentValidator());
    }

    public static void loadProperties(final String bundleName)
            throws ServiceException {
        values.clear();
        if (bundleName == null || bundleName.isEmpty()) {
            throw new ServiceException("Empty bundle name.");
        }
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(bundleName);
            Enumeration<String> keys = bundle.getKeys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                values.put(key, bundle.getString(key));
            }
            isLoad = true;
            initValidators();
        } catch (MissingResourceException e) {
            logger.error("Cannot load '{}' properties file.", bundleName);
            throw new ServiceException("Cannot load properties.");
        }
    }

    public static Map<String, String> getValues() throws ServiceException {
        if (isLoad) {
            return values;
        } else {
            throw new ServiceException("Load properties first.");
        }
    }

    private static void initValidators() throws ServiceException {
        for (Validator validator : validatorList) {
            validator.init();
        }
    }
}
