package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.PlayerDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class PlayerDaoImpl extends AbstractDao implements PlayerDao {
    private static final String SELECT_FROM
            = "SELECT id, profile_photo, nickname, firstName, lastName, birth, "
              + "country_id, overview "
              + "FROM player ";
    private static final String PAGINATION
            = "SELECT id, profile_photo, nickname, firstName, lastName, birth,"
              + " country_id, overview"
              + " FROM player LIMIT ?, ?;";
    private static final String FIND_BY_NICKNAME
            = SELECT_FROM + "WHERE nickname=?;";
    private static final String FIND_BY_FULLNAME
            = SELECT_FROM + "WHERE concat_ws(' ', firstName, lastName) LIKE ?;";
    private static final String GET
            = SELECT_FROM + "WHERE id=?;";
    private static final String SAVE
            = "INSERT INTO player (id, profile_photo, nickname, firstName, "
              + "lastName, birth, country_id, overview) "
              + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE
            = "UPDATE player "
              + "SET id=?, profile_photo=?, nickname=?, firstName=?, lastName=?, "
              + "birth=?, country_id=?, overview=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM player WHERE id=?;";
    private static final String ROWS_NUMBER
            = "SELECT COUNT(*) FROM player;";

    public int getRowsNumber() throws DaoException {
        try (Statement statement = getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(ROWS_NUMBER);
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new DaoException("Cannot count rows.", e);
        }
    }

    @Override
    public Player findByNickname(final String nickname)
            throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(FIND_BY_NICKNAME)) {
            statement.setString(1, nickname);
            return buildSingleInstance(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException("Cannot find player by nickname.", e);
        }
    }

    @Override
    public List<Player> findByFullName(final String fullName)
            throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(FIND_BY_FULLNAME)) {
            statement.setString(1, fullName);
            return buildMultipleInstances(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException("Cannot find player by full name.", e);
        }
    }

    @Override
    public Player get(final long id) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(GET)) {
            statement.setLong(1, id);
            return buildSingleInstance(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException("Cannot get player by id.", e);
        }
    }

    @Override
    public List<Player> getAll() throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(SELECT_FROM)) {
            return buildMultipleInstances(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException("Cannot fetch players.", e);
        }
    }

    public List<Player> getAll(final int offset, final int limit)
            throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(PAGINATION)) {
            statement.setInt(1, offset);
            statement.setInt(2, limit);
            return buildMultipleInstances(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException("Cannot fetch players.", e);
        }
    }

    @Override
    public boolean save(final Player entity) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(SAVE)) {
            setUpStatement(statement, entity);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            return !isDuplicateError(e, "Cannot save player.");
        }
    }

    @Override
    public boolean update(final Player entity) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(UPDATE)) {
            setUpStatement(statement, entity);
            statement.setLong(9, entity.getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            return !isDuplicateError(e, "Cannot update player.");
        }
    }

    @Override
    public void delete(final Player entity) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(DELETE)) {
            statement.setLong(1, entity.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Cannot delete player.", e);
        }
    }

    private Player buildSingleInstance(final ResultSet rs) throws SQLException {
        if (rs.next()) {
            return buildPlayer(rs);
        }
        return null;
    }

    private List<Player> buildMultipleInstances(final ResultSet rs) throws SQLException {
        List<Player> players = new ArrayList<>();
        while (rs.next()) {
            players.add(buildPlayer(rs));
        }
        return players;
    }

    private Player buildPlayer(final ResultSet rs) throws SQLException {
        Player player = new Player();
        player.setId(rs.getInt("id"));
        player.setProfilePhoto(rs.getString("profile_photo"));
        player.setNickname(rs.getString("nickname"));
        player.setFirstName(rs.getString("firstName"));
        player.setLastName(rs.getString("lastName"));
        player.setBirth(LocalDate.parse(rs.getString("birth")));
        Country c = new Country();
        c.setId(rs.getLong("country_id"));
        player.setCountry(c);
        player.setOverview(rs.getString("overview"));
        return player;
    }

    private void setUpStatement(final PreparedStatement st, final Player player)
            throws SQLException {
        int index = 1;
        st.setLong(index++, player.getId());
        st.setString(index++, player.getProfilePhoto());
        st.setString(index++, player.getNickname());
        st.setString(index++, player.getFirstName());
        st.setString(index++, player.getLastName());
        st.setString(index++, player.getBirth().toString());
        st.setLong(index++, player.getCountry().getId());
        st.setString(index, player.getOverview());
    }
}
