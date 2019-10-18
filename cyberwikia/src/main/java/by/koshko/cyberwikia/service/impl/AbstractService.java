package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.RawData;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AbstractService {

    private Logger logger = LogManager.getLogger(getClass());

    private ServiceFactory factory;
    private Transaction transaction;

    protected Transaction getTransaction() {
        return transaction;
    }

    protected ServiceFactory getFactory() {
        return factory;
    }

    public AbstractService(final Transaction externalTransaction,
                           final ServiceFactory extFactory) {
        factory = extFactory;
        transaction = externalTransaction;
    }

    protected String saveNewDeleteOldImage(final String oldImage,
                                         final RawData newData) {
        if (newData == null) {
            return oldImage;
        }
        String newImagePath = ServiceFactory
                .getImageService().save(newData);
        if (newImagePath != null) {
            logger.debug("New profile picture is set up: '{}'.", newImagePath);
            ServiceFactory.getImageService().delete(oldImage);
            return newImagePath;
        } else {
            return oldImage;
        }
    }
}
