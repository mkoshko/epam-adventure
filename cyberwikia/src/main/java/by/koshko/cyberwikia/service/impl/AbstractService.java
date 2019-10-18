package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.ServiceFactory;

public class AbstractService {
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
}
