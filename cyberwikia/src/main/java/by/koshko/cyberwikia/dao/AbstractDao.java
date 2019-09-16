package by.koshko.cyberwikia.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AbstractDao {
    protected final Logger logger = LogManager.getLogger(getClass());
    private Connection connection;

    public void setConnection(final Connection conn) {
        connection = conn;
    }

    public Connection getConnection() {
        return connection;
    }

    protected void requireNonNullConnection(final String logMsg)
            throws DaoException {
        if (connection == null) {
            logger.error(logMsg);
            throw new DaoException("Connection is null.");
        }
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
}
