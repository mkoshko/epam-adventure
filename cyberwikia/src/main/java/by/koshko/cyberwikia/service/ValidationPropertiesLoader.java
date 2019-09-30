package by.koshko.cyberwikia.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class ValidationPropertiesLoader {

    private ValidationPropertiesLoader() {
    }

    private static Logger logger = LogManager.getLogger(ValidationPropertiesLoader.class);
    private static Map<String, String> values = new HashMap<>();
    private static boolean isLoad = false;

    public static void loadProperties(final String name) throws ServiceException {
        values.clear();
        if (name == null || name.isEmpty()) {
            throw new ServiceException("Empty bundle name.");
        }
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(name);
            Enumeration<String> keys = bundle.getKeys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                values.put(key, bundle.getString(key));
            }
            isLoad = true;
            logger.debug("Validation properties is loaded. {}", values);
        } catch (MissingResourceException e) {
            logger.error("Cannot load '{}' properties file.", name);
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

}
