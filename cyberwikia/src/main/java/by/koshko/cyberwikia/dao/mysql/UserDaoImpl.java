package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.UserDao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public final class UserDaoImpl extends AbstractDao implements UserDao {
    private static final String FIND_BY_LOGIN_QUERY
            = "SELECT id, login, email, password, role "
              + "FROM user "
              + "WHERE BINARY login=?;";
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
    private static final String LOGIN_EXISTS = "{CALL login_exists (?,?)}";
    private static final String EMAIL_EXISTS = "{CALL email_exists (?,?)}";

    public UserDaoImpl(final Connection newConnection) {
        super(newConnection);
    }

    @Override
    public boolean hasLogin(final String login) throws DaoException {
        try (CallableStatement statement
                     = getConnection().prepareCall(LOGIN_EXISTS)) {
            statement.setString(1, login);
            statement.registerOutParameter(2, Types.BOOLEAN);
            statement.execute();
            return statement.getBoolean(2);
        } catch (SQLException e) {
            throw new DaoException("Cannot perform operation.", e);
        }
    }

    public boolean hasEmail(final String email) throws DaoException {
        try (CallableStatement statement
                     = getConnection().prepareCall(EMAIL_EXISTS)) {
            statement.setString(1, email);
            statement.registerOutParameter(2, Types.BOOLEAN);
            statement.execute();
            return statement.getBoolean(2);
        } catch (SQLException e) {
            throw new DaoException("Cannot perform operation.", e);
        }
    }

    @Override
    public User findByLogin(final String login) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(FIND_BY_LOGIN_QUERY)) {
            statement.setString(1, login);
            return buildSingleInstance(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException("Cannot get user by login.", e);
        }
    }

    @Override
    public User get(final long id) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(GET_QUERY)) {
            statement.setLong(1, id);
            return buildSingleInstance(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException("Cannot get user by id.", e);
        }
    }

    @Override
    public List<User> getAll() throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(GET_ALL_QUERY)) {
            return buildMultipleInstances(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException("Cannot get all users.", e);
        }
    }

    @Override
    public boolean save(final User entity) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(SAVE_QUERY)) {
            setUpStatement(statement, entity);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            return !isDuplicateError(e, "Cannot save user.");
        }
    }

    @Override
    public boolean update(final User entity) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(UPDATE_QUERY)) {
            setUpStatement(statement, entity);
            statement.setLong(5, entity.getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            return !isDuplicateError(e, "Cannot update user.");
        }
    }

    @Override
    public boolean delete(final User entity) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(DELETE_QUERY)) {
            statement.setLong(1, entity.getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("Cannot remove the user.", e);
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
        st.setString(1, user.getLogin());
        st.setString(2, user.getEmail());
        st.setString(3, user.getPassword());
        st.setInt(4, user.getRole().ordinal());
    }
}
