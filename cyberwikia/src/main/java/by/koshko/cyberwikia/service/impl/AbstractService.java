package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.dao.mysql.TransactionImpl;
import by.koshko.cyberwikia.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AbstractService {

    private Logger logger = LogManager.getLogger(getClass());
    private Transaction transaction;
    private boolean isAutoClose;

    protected Transaction getTransaction() {
        return transaction;
    }

    protected Logger getLogger() {
        return logger;
    }

    public AbstractService() throws ServiceException {
        try {
            transaction = new TransactionImpl();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public AbstractService(final Transaction transaction) {
        this.transaction = transaction;
        isAutoClose = true;
    }

    protected void close() {
        if (!isAutoClose) {
            transaction.close();
        }
    }
}
