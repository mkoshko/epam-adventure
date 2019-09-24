package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.bean.Entity;

public interface Transaction {
    <T extends Dao<? extends Entity>> T getDao(DaoTypes type) throws DaoException;
    void close();
}
