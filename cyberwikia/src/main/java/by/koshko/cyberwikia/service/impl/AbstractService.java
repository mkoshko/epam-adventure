package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.dao.mysql.TransactionImpl;
import by.koshko.cyberwikia.service.ServiceException;

public class AbstractService {
    private Transaction transaction;
    private boolean isAutoClose;

    protected Transaction getTransaction() {
        return transaction;
    }

    public AbstractService() throws ServiceException {
        try {
            transaction = new TransactionImpl();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public AbstractService(final Transaction externalTransaction) {
        transaction = externalTransaction;
        isAutoClose = true;
    }

    public void close() {
        if (!isAutoClose) {
            transaction.close();
        }
    }
}
