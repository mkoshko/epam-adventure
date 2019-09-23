package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.dao.TransactionImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AbstractService {

    protected Logger logger = LogManager.getLogger(getClass());
    protected Transaction transaction;
    protected boolean autoClose;

    public AbstractService() throws ServiceException {
        try {
            transaction = new TransactionImpl();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public AbstractService(final Transaction transaction) {
        this.transaction = transaction;
        autoClose = true;
    }

    protected void close() {
        if (!autoClose) {
            transaction.close();
        }
    }
}
