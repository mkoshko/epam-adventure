package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.dao.Connectable;
import by.koshko.cyberwikia.dao.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class AbstractDao implements Connectable {
    private Logger logger = LogManager.getLogger(getClass());
    private Connection connection;

    public AbstractDao(final Connection newConnection) {
        connection = newConnection;
    }

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
            logger.debug(e.getMessage());
            return true;
        } else {
            throw new DaoException(msg, e);
        }
    }
}
