package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.dao.AbstractDao;
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
    private static final int TABLE_PLAYER_ID = 1;
    private static final int TABLE_PLAYER_PROFILE_PHOTO = 2;
    private static final int TABLE_PLAYER_NICKNAME = 3;
    private static final int TABLE_PLAYER_FIRST_NAME = 4;
    private static final int TABLE_PLAYER_LAST_NAME = 5;
    private static final int TABLE_PLAYER_BIRTH = 6;
    private static final int TABLE_PLAYER_COUNTRY = 7;
    private static final int TABLE_PLAYER_OVERVIEW = 8;

    private static final int UPD_PLAYER_PHOTO = 1;
    private static final int UPD_PLAYER_NICKNAME = 2;
    private static final int UPD_PLAYER_FIRST_NAME = 3;
    private static final int UPD_PLAYER_LAST_NAME = 4;
    private static final int UPD_PLAYER_BIRTH = 5;
    private static final int UPD_PLAYER_COUNTRY = 6;
    private static final int UPD_PLAYER_OVERVIEW = 7;
    private static final int UPD_PLAYER_ID = 8;

    private static final String SELECT_STATEMENT = "SELECT player.id, "
            + "profile_photo, nickname, firstName, lastName, birth, "
            + "country.name as country, overview "
            + "FROM player "
            + "join country ON player.country_id = country.id ";

    @Override
    public Optional<Player> findByNickname(final String nickname)
            throws DaoException {
        String query = SELECT_STATEMENT
                     + "WHERE nickname=?;";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, nickname);
            rs = statement.executeQuery();
            return Optional.ofNullable(buildSingleInstance(rs));
        } catch (SQLException e) {
            logger.error("Cannot find player. SQL state: {}. Message: {}",
                             e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot fetch player from database "
                                 + "due to internal errors.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public List<Player> findByFullName(final String fullName)
            throws DaoException {
        String query = SELECT_STATEMENT
                     + "WHERE concat_ws(' ', firstName, lastName) LIKE ?;";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, String.format("%s", fullName));
            rs = statement.executeQuery();
            return buildMultipleInstances(rs);
        } catch (SQLException e) {
            logger.error("Cannot find player by full name. SQL state: {}."
                    + " Message: {}", e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot fetch player from database "
                                 + "due to internal errors.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public Optional<Player> get(final long id) throws DaoException {
        String query = SELECT_STATEMENT
                    + "WHERE player.id=?;";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setLong(1, id);
            rs = statement.executeQuery();
            return Optional.ofNullable(buildSingleInstance(rs));
        } catch (SQLException e) {
            logger.error("Cannot find player by id. SQL state: {}. Message: {}",
                            e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot fetch player from database "
                                 + "due to internal errors.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public List<Player> getAll() throws DaoException {
        String query = SELECT_STATEMENT + ";";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(query);
            rs = statement.executeQuery();
            return buildMultipleInstances(rs);
        } catch (SQLException e) {
            logger.error("Cannot find all players. SQL state: {}. Message: {}",
                             e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot fetch player from database "
                                 + "due to internal errors.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public void save(final Player entity) throws DaoException {
        String query = "INSERT INTO player (profile_photo, nickname, firstName,"
                 + " lastName, birth, country_id, overview) "
                 + " VALUES (?, ?, ?, ?, ?,"
                 + " (SELECT country.id FROM country WHERE country.name=?), ?)";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            setUpStatement(statement, entity);
            if (statement.executeUpdate() == 1) {
                logger.info("'{}' was saved successfully.", entity.getNickname());
            }
        } catch (SQLException e) {
            logger.error("Cannot save player. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot save player to database "
                    + "due to internal errors.");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void update(final Player entity) throws DaoException {
        String query = "UPDATE player "
                + "SET profile_photo=?, nickname=?, firstName=?, lastName=?, "
                + "birth=?, "
                + "country_id=(SELECT country_id FROM country WHERE name=?), "
                + "overview=? "
                + "WHERE id=?;";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
            setUpStatement(statement, entity);
            statement.setLong(UPD_PLAYER_ID, entity.getId());
            if (statement.executeUpdate() == 1) {
                logger.info("'{}' was updated successfully.",
                        entity.getNickname());
            }
        } catch (SQLException e) {
            logger.error("Cannot save player. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot update player information in"
                    + " database due to internal errors.");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void delete(final Player entity) throws DaoException {
        Connection connection = getConnection();
        String query = "DELETE FROM player WHERE id=?;";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, entity.getId());
            if (statement.executeUpdate() == 1) {
                logger.info("'{}' profile has been successfully removed.",
                        entity.getNickname());
            }
        } catch (SQLException e) {
            logger.error("Cannot delete player. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot delete player profile from database "
                    + "due to internal errors.");
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
        logger.debug("Building 'Player' object...");
        Player player = new Player();
        logger.debug("Setting player id.");
        player.setId(rs.getInt(TABLE_PLAYER_ID));
        logger.debug("Setting player profile_photo.");
        player.setProfilePhoto(rs.getString(TABLE_PLAYER_PROFILE_PHOTO));
        logger.debug("Setting player nickname.");
        player.setNickname(rs.getString(TABLE_PLAYER_NICKNAME));
        logger.debug("Setting player first name.");
        player.setFirstName(rs.getString(TABLE_PLAYER_FIRST_NAME));
        logger.debug("Setting player last name.");
        player.setLastName(rs.getString(TABLE_PLAYER_LAST_NAME));
        logger.debug("Setting player birth.");
        player.setBirth(LocalDate.parse(rs.getString(TABLE_PLAYER_BIRTH)));
        logger.debug("Setting player country.");
        player.setCountry(rs.getString(TABLE_PLAYER_COUNTRY));
        logger.debug("Setting player overview.");
        player.setOverview(rs.getString(TABLE_PLAYER_OVERVIEW));
        logger.debug("'Player' object has been successfully built.");
        return player;
    }

    private void setUpStatement(final PreparedStatement st, final Player obj)
            throws SQLException {
        st.setString(UPD_PLAYER_PHOTO, obj.getProfilePhoto());
        st.setString(UPD_PLAYER_NICKNAME, obj.getNickname());
        st.setString(UPD_PLAYER_FIRST_NAME, obj.getFirstName());
        st.setString(UPD_PLAYER_LAST_NAME, obj.getLastName());
        st.setString(UPD_PLAYER_BIRTH, obj.getBirth().toString());
        st.setString(UPD_PLAYER_COUNTRY, obj.getCountry());
        st.setString(UPD_PLAYER_OVERVIEW, obj.getOverview());
    }
}
