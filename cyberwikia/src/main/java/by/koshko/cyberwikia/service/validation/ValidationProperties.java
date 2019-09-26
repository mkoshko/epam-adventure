package by.koshko.cyberwikia.service.validation;

import by.koshko.cyberwikia.service.PropertiesConverter;
import by.koshko.cyberwikia.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class ValidationProperties {
    private final Logger logger = LogManager.getLogger(ValidationProperties.class);
    private static final ValidationProperties INSTANCE = new ValidationProperties();
    private final String res = "validation.properties";
    private Map<String, Integer> permissibleValues;

    private ValidationProperties() {
    }

    public void init() throws ServiceException {
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(res);
            if (in == null) {
                throw new ServiceException("Cannot load properties for " + getClass());
            }
            Properties validParams = new Properties();
            validParams.load(in);
            permissibleValues = PropertiesConverter.convertToInt(validParams);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new ServiceException("Cannot init validation factory");
        }
    }

    public static ValidationProperties getInstance() {
        return INSTANCE;
    }

    public Map<String, Integer> getPermissibleValues() {
        return permissibleValues;
    }
}
