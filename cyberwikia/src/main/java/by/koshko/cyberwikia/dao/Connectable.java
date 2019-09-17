package by.koshko.cyberwikia.dao;

import java.sql.Connection;

public interface Connectable {
    void setConnection(Connection connection) throws DaoException;
}
