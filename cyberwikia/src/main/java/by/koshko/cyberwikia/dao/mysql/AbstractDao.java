package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Entity;
import by.koshko.cyberwikia.dao.Connectable;
import by.koshko.cyberwikia.dao.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AbstractDao implements Connectable {
    protected final Logger logger = LogManager.getLogger(getClass());
    private Connection connection;

    public void setConnection(final Connection conn) throws DaoException {
        requireNonNullConnection(conn);
        connection = conn;
    }

    public Connection getConnection() throws DaoException {
        requireNonNullConnection(connection);
        return connection;
    }

    protected final void closeStatement(final Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (Exception e) {
            logger.error("Error during closing statement. {}", e.getMessage());
        }
    }

    protected final void closeResultSet(final ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            logger.error("Error during closing result set. {}", e.getMessage());
        }
    }

    private void requireNonNullConnection(final Connection conn)
            throws DaoException {
        if (conn == null) {
            throw new DaoException("Connection is null.");
        }
    }

    protected void requireNonNullEntity(final Entity entity) throws DaoException {
        if (entity == null) {
            throw new DaoException("Cannot perform operations with null entity.");
        }
    }
}
