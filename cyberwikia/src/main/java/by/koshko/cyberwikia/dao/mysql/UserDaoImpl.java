package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.UserDao;
import by.koshko.cyberwikia.dao.mysql.AbstractDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class UserDaoImpl extends AbstractDao implements UserDao {
    private static final String FIND_BY_LOGIN_QUERY
            = "SELECT id, login, email, password, role "
              + "FROM user "
              + "WHERE login=?;";
    private static final String GET_QUERY
            = "SELECT id, login, email, password, role "
              + "FROM user "
              + "WHERE id=?;";
    private static final String GET_ALL_QUERY
            = "SELECT id, login, email, password, role "
              + "FROM user;";
    private static final String SAVE_QUERY
            = "INSERT INTO user(login, email, password, role) "
              + "VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY
            = "UPDATE user "
              + "SET login=?, email=?, password=?, role=? "
              + "WHERE id=?;";
    private static final String DELETE_QUERY = "DELETE FROM user WHERE id=?;";

    @Override
    public User findByLogin(final String login) throws DaoException {
        PreparedStatement statement = null;
        if (login == null) {
            logger.warn("Attempt to find user by login with null argument.");
        }
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(FIND_BY_LOGIN_QUERY);
            statement.setString(1, login);
            rs = statement.executeQuery();
            return buildSingleInstance(rs);
        } catch (SQLException e) {
            logger.error("Cannot find user by login. SQL state: {}. "
                         + "Message: {}", e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot fetch user.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public User get(final long id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(GET_QUERY);
            statement.setLong(1, id);
            rs = statement.executeQuery();
            return buildSingleInstance(rs);
        } catch (SQLException e) {
            logger.error("Cannot find user by id. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot fetch user.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public List<User> getAll() throws DaoException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(GET_ALL_QUERY);
            rs = statement.executeQuery();
            return buildMultipleInstances(rs);
        } catch (SQLException e) {
            logger.error("Cannot fetch all users. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot fetch users.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public void save(final User entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(SAVE_QUERY);
            setUpStatement(statement, entity);
            if (statement.executeUpdate() == 1) {
                logger.info("User '{}' has been saved to database.",
                        entity.getLogin());
            }
        } catch (SQLException e) {
            logger.error("Cannot save the user. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot save the user.");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void update(final User entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(UPDATE_QUERY);
            setUpStatement(statement, entity);
            statement.setLong(5, entity.getId());
            if (statement.executeUpdate() == 1) {
                logger.info("User '{}' has been updated.",
                        entity.getLogin());
            }
        } catch (SQLException e) {
            logger.error("Cannot update the user. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot update the user in a database.");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void delete(final User entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(DELETE_QUERY);
            statement.setLong(1, entity.getId());
            if (statement.executeUpdate() == 1) {
                logger.info(" User '{}' has been removed.",
                        entity.getLogin());
            }
        } catch (SQLException e) {
            logger.error("Cannot delete user. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot remove the user.");
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
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setLogin(rs.getString("login"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getInt("role"));
        return user;
    }

    private void setUpStatement(final PreparedStatement st, final User user)
            throws SQLException {
        int index = 1;
        st.setString(index++, user.getLogin());
        st.setString(index++, user.getEmail());
        st.setString(index++, user.getPassword());
        st.setInt(index, user.getRole().ordinal());
    }
}
