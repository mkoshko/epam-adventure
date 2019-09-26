package by.koshko.cyberwikia.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class PropertiesConverter {
    private static Logger logger = LogManager.getLogger(PropertiesConverter.class);
    private PropertiesConverter() {
    }
    public static Map<String, Integer> convertToInt(final Properties properties)
            throws ServiceException {
        Enumeration<Object> keys = properties.keys();
        Iterator<Object> iterator = keys.asIterator();
        Map<String, Integer> converted = new HashMap<>();
        while (iterator.hasNext()) {
            String key = String.valueOf(iterator.next());
            try {
                Integer value = Integer.parseInt(properties.getProperty(key));
                converted.put(key, value);
            } catch (NumberFormatException e) {
                logger.error("Cannot convert '{}' value.", key);
                throw new ServiceException("Cannot convert value from property file to integer.");
            }
        }
        return converted;
    }
}
