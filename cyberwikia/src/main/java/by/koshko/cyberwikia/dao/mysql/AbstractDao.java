package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.dao.Connectable;
import by.koshko.cyberwikia.dao.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public class AbstractDao implements Connectable {
    private Connection connection;

    public void setConnection(final Connection conn) throws DaoException {
        requireNonNullConnection(conn);
        connection = conn;
    }

    protected Connection getConnection() throws DaoException {
        requireNonNullConnection(connection);
        return connection;
    }

    private void requireNonNullConnection(final Connection conn)
            throws DaoException {
        if (conn == null) {
            throw new DaoException("Connection is null.");
        }
    }

    protected boolean isDuplicateError(final SQLException e, final String msg)
            throws DaoException {
        if (e.getErrorCode() == 1062) {
            return true;
        } else {
            throw new DaoException(msg, e);
        }
    }
}
