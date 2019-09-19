package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.PlayerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class PlayerDaoImpl extends AbstractDao implements PlayerDao {
    private static final int PLAYER_PHOTO = 1;
    private static final int PLAYER_NICKNAME = 2;
    private static final int PLAYER_FIRST_NAME = 3;
    private static final int PLAYER_LAST_NAME = 4;
    private static final int PLAYER_BIRTH = 5;
    private static final int PLAYER_COUNTRY = 6;
    private static final int PLAYER_OVERVIEW = 7;
    private static final int PLAYER_ID = 8;

    private static final String SELECT_FROM
            = "SELECT id, profile_photo, nickname, firstName, lastName, birth, "
              + "country_id, overview "
              + "FROM player ";
    private static final String FIND_BY_NICKNAME_QUERY
            = SELECT_FROM + "WHERE nickname=?;";
    private static final String FIND_BY_FULLNAME_QUERY
            = SELECT_FROM + "WHERE concat_ws(' ', firstName, lastName) LIKE ?;";
    private static final String GET_QUERY
            = SELECT_FROM + "WHERE id=?;";
    private static final String SAVE_QUERY
            = "INSERT INTO player (profile_photo, nickname, firstName, "
              + "lastName, birth, country_id, overview) "
              + "VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_PLAYER_QUERY
            = "UPDATE player "
              + "SET profile_photo=?, nickname=?, firstName=?, lastName=?, "
              + "birth=?, country_id=?, overview=? WHERE id=?;";
    private static final String DELETE_QUERY = "DELETE FROM player WHERE id=?;";

    @Override
    public Optional<Player> findByNickname(final String nickname)
            throws DaoException {
        if (nickname == null) {
            logger.warn("Attempt to find player by nickname with null"
                        + " argument.");
        }
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(FIND_BY_NICKNAME_QUERY);
            statement.setString(1, nickname);
            rs = statement.executeQuery();
            return Optional.ofNullable(buildSingleInstance(rs));
        } catch (SQLException e) {
            logger.error("Cannot find player by nickname. SQL state: {}."
                         + " Message: {}", e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot fetch player.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public List<Player> findByFullName(final String fullName)
            throws DaoException {
        if (fullName == null) {
            logger.warn("Attempt to find player by full name with null"
                        + " argument.");
        }
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(FIND_BY_FULLNAME_QUERY);
            statement.setString(1, fullName);
            rs = statement.executeQuery();
            return buildMultipleInstances(rs);
        } catch (SQLException e) {
            logger.error("Cannot find player by full name. SQL state: {}."
                         + " Message: {}", e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot fetch player.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public Optional<Player> get(final long id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(GET_QUERY);
            statement.setLong(1, id);
            rs = statement.executeQuery();
            return Optional.ofNullable(buildSingleInstance(rs));
        } catch (SQLException e) {
            logger.error("Cannot find player by id. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot fetch player.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public List<Player> getAll() throws DaoException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(SELECT_FROM);
            rs = statement.executeQuery();
            return buildMultipleInstances(rs);
        } catch (SQLException e) {
            logger.error("Cannot find all players. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot fetch players.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public void save(final Player entity) throws DaoException {
        requireNonNullEntity(entity);
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(SAVE_QUERY);
            setUpStatement(statement, entity);
            if (statement.executeUpdate() == 1) {
                logger.info("Player profile '{}' has been saved to database.",
                        entity.getNickname());
            }
        } catch (SQLException e) {
            logger.error("Cannot save player. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot save player.");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void update(final Player entity) throws DaoException {
        requireNonNullEntity(entity);
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(UPDATE_PLAYER_QUERY);
            setUpStatement(statement, entity);
            statement.setLong(PLAYER_ID, entity.getId());
            if (statement.executeUpdate() == 1) {
                logger.info("Player profile '{}' has been updated.",
                        entity.getNickname());
            }
        } catch (SQLException e) {
            logger.error("Cannot save player. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot update player.");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void delete(final Player entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(DELETE_QUERY);
            statement.setLong(1, entity.getId());
            if (statement.executeUpdate() == 1) {
                logger.info("Player profile'{}' has been removed.",
                        entity.getNickname());
            }
        } catch (SQLException e) {
            logger.error("Cannot delete player. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot delete player.");
        } finally {
            closeStatement(statement);
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
        logger.debug("Building Player object.");
        Player player = new Player();
        player.setId(rs.getInt("id"));
        player.setProfilePhoto(rs.getString("profile_photo"));
        player.setNickname(rs.getString("nickname"));
        player.setFirstName(rs.getString("firstName"));
        player.setLastName(rs.getString("lastName"));
        player.setBirth(LocalDate.parse(rs.getString("birth")));
        player.setCountryID(rs.getLong("country_id"));
        player.setOverview(rs.getString("overview"));
        logger.debug("Player object was build successfully.");
        return player;
    }

    private void setUpStatement(final PreparedStatement st, final Player obj)
            throws SQLException {
        logger.debug("Preparing statement for execution.");
        st.setString(PLAYER_PHOTO, obj.getProfilePhoto());
        st.setString(PLAYER_NICKNAME, obj.getNickname());
        st.setString(PLAYER_FIRST_NAME, obj.getFirstName());
        st.setString(PLAYER_LAST_NAME, obj.getLastName());
        st.setString(PLAYER_BIRTH, obj.getBirth().toString());
        st.setLong(PLAYER_COUNTRY, obj.getCountryID());
        st.setString(PLAYER_OVERVIEW, obj.getOverview());
    }
}
