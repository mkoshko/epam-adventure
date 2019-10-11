package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.bean.Entity;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Entity> extends Connectable {

    T get(long id) throws DaoException;

    List<T> getAll() throws DaoException;

    boolean save(T entity) throws DaoException;

    boolean update(T entity) throws DaoException;

    void delete(T entity) throws DaoException;
}
