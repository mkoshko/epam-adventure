package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.RawData;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.pool.ConnectionPool;
import by.koshko.cyberwikia.service.validation.ValidationPropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class ServiceInitializer {
    /**
     * Private constructor.
     */
    private ServiceInitializer() {
    }

    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger();

    /**
     * Initializes services.
     *
     * @param config Properties for services initialization.
     * @throws ServiceException if some services cannot be initialized.
     */
    public static void init(final Properties config) throws ServiceException {
        try {
            logger.debug("Initializing services.");
            ConnectionPool.getInstance().init(config.getProperty("database"));
            ValidationPropertiesLoader
                    .loadProperties(config.getProperty("validation"));
            RawData.setRootPath(config.getProperty("rootPath"));
            logger.debug("Services initialized successfully.");
        } catch (DaoException e) {
            throw new ServiceException("Cannot initialize services.", e);
        }
    }
}
