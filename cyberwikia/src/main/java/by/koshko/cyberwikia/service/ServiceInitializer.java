package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.RawData;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.cyberpool.ConnectionPool;
import by.koshko.cyberwikia.service.validation.ValidationPropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class ServiceInitializer {
    private static Logger logger = LogManager.getLogger(ServiceInitializer.class);
    public static void init(final Properties config) throws ServiceException {
        try {
            ConnectionPool.getInstance().init();
            ValidationPropertiesLoader.loadProperties(config.getProperty("bundle"));
            RawData.setRootPath(config.getProperty("path"));
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException("Cannot initialize services.");
        }
    }
}
