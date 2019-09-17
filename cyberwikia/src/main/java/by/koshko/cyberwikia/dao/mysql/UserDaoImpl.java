package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.dao.AbstractDao;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class UserDaoImpl extends AbstractDao implements UserDao {
    private static final int TB_USER_ID = 1;
    private static final int TB_USER_LOGIN = 2;
    private static final int TB_USER_EMAIL = 3;
    private static final int TB_USER_PASSWORD = 4;
    private static final int TB_USER_ROLE = 5;

    private static final int USER_LOGIN = 1;
    private static final int USER_EMAIL = 2;
    private static final int USER_PASSWORD = 3;
    private static final int USER_ROLE = 4;
    private static final int USER_ID = 5;

    private static final String SELECT_STATEMENT
            = "SELECT id, login, email, password, role FROM user";

    @Override
    public Optional<User> findByLogin(final String login) throws DaoException {
        Connection connection = getConnection();
        String query = SELECT_STATEMENT  + " WHERE login=?;";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, login);
            rs = statement.executeQuery();
            return Optional.ofNullable(buildSingleInstance(rs));
        } catch (SQLException e) {
            logger.error("Cannot find user by login. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
    //TODO https://docs.oracle.com/javase/tutorial/jdbc/basics/sqlexception.html
            throw new DaoException("Cannot fetch user from database.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public Optional<User> get(final long id) throws DaoException {
        Connection connection = getConnection();
        String query = SELECT_STATEMENT + " WHERE id=?;";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            rs = statement.executeQuery();
            return Optional.ofNullable(buildSingleInstance(rs));
        } catch (SQLException e) {
            logger.error("Cannot find user by id. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot fetch user from database.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public List<User> getAll() throws DaoException {
        Connection connection = getConnection();
        String query = SELECT_STATEMENT + ";";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();
            return buildMultipleInstances(rs);
        } catch (SQLException e) {
            logger.error("Cannot fetch all users. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot fetch users from database.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public void save(final User entity) throws DaoException {
        Connection connection = getConnection();
        String query = "INSERT INTO user(login, email, password, role) "
                     + "VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            setUpStatement(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot save user. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot save the user in a database.");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void update(final User entity) throws DaoException {
        Connection connection = getConnection();
        String query = "UPDATE user "
                + "SET login=?, email=?, password=?, role=? "
                + "WHERE id=?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            setUpStatement(statement, entity);
            statement.setLong(USER_ID, entity.getId());
            var code = statement.executeUpdate();
            if (code == 1) {
                logger.info("'{}' was successfully updated.", entity.getLogin());
            }
        } catch (SQLException e) {
            logger.error("Cannot update user. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot update the user in a database.");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void delete(final User entity) throws DaoException {
        Connection connection = getConnection();
        String query = "DELETE FROM user "
                     + "WHERE id=?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, entity.getId());
            var code = statement.executeUpdate();
            if (code == 1) {
                logger.info("'{}' was successfully removed.", entity.getLogin());
            }
        } catch (SQLException e) {
            logger.error("Cannot delete user. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot remove the user from a database.");
        } finally {
            closeStatement(statement);
        }
    }

    private User buildSingleInstance(final ResultSet rs) throws SQLException {
        if (rs.next()) {
            return buildUser(rs);
        } else {
            return null;
        }
    }

    private List<User> buildMultipleInstances(final ResultSet rs)
            throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add(buildUser(rs));
        }
        return users;
    }

    private User buildUser(final ResultSet rs) throws SQLException {
        logger.debug("Building 'User' object...");
        User user = new User();
        logger.debug("Setting user id.");
        user.setId(rs.getLong(TB_USER_ID));
        logger.debug("Setting user login.");
        user.setLogin(rs.getString(TB_USER_LOGIN));
        logger.debug("Setting user email.");
        user.setEmail(rs.getString(TB_USER_EMAIL));
        logger.debug("Setting user password.");
        user.setPassword(rs.getString(TB_USER_PASSWORD));
        logger.debug("Setting user role.");
        user.setRole(rs.getInt(TB_USER_ROLE));
        return user;
    }

    private void setUpStatement(final PreparedStatement st, final User user)
            throws SQLException {
        st.setString(USER_LOGIN, user.getLogin());
        st.setString(USER_EMAIL, user.getEmail());
        st.setString(USER_PASSWORD, user.getPassword());
        st.setInt(USER_ROLE, user.getRole());
    }
}
