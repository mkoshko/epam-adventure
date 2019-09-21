package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.bean.Entity;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Entity> extends Connectable {

    Optional<T> get(long id) throws DaoException;

    List<T> getAll() throws DaoException;

    void save(T entity) throws DaoException;

    void update(T entity) throws DaoException;

    void delete(T entity) throws DaoException;
}