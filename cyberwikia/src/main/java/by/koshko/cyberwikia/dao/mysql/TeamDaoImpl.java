package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.bean.Game;
import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.TeamDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class TeamDaoImpl extends AbstractDao implements TeamDao {
    private static final int TEAM_NAME = 1;
    private static final int LOGO_FILE = 2;
    private static final int COUNTRY_ID = 3;
    private static final int CREATOR_ID = 4;
    private static final int CAPTAIN_ID = 5;
    private static final int COACH_ID = 6;
    private static final int GAME_ID = 7;
    private static final int OVERVIEW = 8;
    private static final int TEAM_ID = 9;
    private static final String FIND_BY_NAME_QUERY
            = "SELECT id, name, logo_file, country_id, creator, captain, coach,"
              + " game, overview FROM team WHERE name=?;";
    private static final String GET_QUERY
            = "SELECT id, name, logo_file, country_id, creator, captain, coach,"
                    + " game, overview FROM team WHERE id=?;";
    private static final String GET_ALL_QUERY
            = "SELECT id, name, logo_file, country_id, creator, captain, coach,"
              + " game, overview FROM team;";
    private static final String UPDATE_QUERY
            = "UPDATE team SET name=?, logo_file=?, country_id=?, creator=?, "
              + "captain=?, coach=?, game=?, overview=? WHERE id=?;";
    private static final String DELETE_QUERY = "DELETE FROM team WHERE id=?";
    private static final String SAVE_QUERY
            = "INSERT INTO team (name, logo_file, country_id, creator,"
            + " captain, coach, game, overview)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public Team findByName(final String name) throws DaoException {
        if (name == null) {
            logger.warn("Attempt to find team by name with null argument.");
        }
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(FIND_BY_NAME_QUERY);
            statement.setString(1, name);
            rs = statement.executeQuery();
            return buildSingleInstance(rs);
        } catch (SQLException e) {
            logger.error("Cannot find team by name. SQL state: {}."
                         + " Message: {}", e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot find team by name.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public Team get(final long id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(GET_QUERY);
            statement.setLong(1, id);
            rs = statement.executeQuery();
            return buildSingleInstance(rs);
        } catch (SQLException e) {
            logger.error("Cannot find team by ID. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot find team by id.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public List<Team> getAll() throws DaoException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(GET_ALL_QUERY);
            rs = statement.executeQuery();
            return buildMultipleInstances(rs);
        } catch (SQLException e) {
            logger.error("Cannot fetch all teams. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot fetch all teams.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public void save(final Team entity) throws DaoException {
        requireNonNullEntity(entity);
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(SAVE_QUERY);
            setUpStatement(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot save team. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot save team.");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void update(final Team entity) throws DaoException {
        requireNonNullEntity(entity);
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(UPDATE_QUERY);
            setUpStatement(statement, entity);
            statement.setLong(TEAM_ID, entity.getId());
            if (statement.executeUpdate() == 1) {
                logger.info("Team '{}' has been updated", entity.getName());
            }
        } catch (SQLException e) {
            logger.error("Cannot update team. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot update team.");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void delete(final Team entity) throws DaoException {
        requireNonNullEntity(entity);
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(DELETE_QUERY);
            statement.setLong(1, entity.getId());
            if (statement.executeUpdate() == 1) {
                logger.info("'{}' was successfully removed.", entity.getName());
            }
        } catch (SQLException e) {
            logger.error("Cannot remove team. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot remove team.");
        } finally {
            closeStatement(statement);
        }
    }

    private Team buildSingleInstance(final ResultSet rs) throws SQLException {
        if (rs.next()) {
            return buildTeam(rs);
        }
        return null;
    }

    private List<Team> buildMultipleInstances(final ResultSet rs)
            throws SQLException {
        List<Team> teams = new ArrayList<>();
        while (rs.next()) {
            teams.add(buildTeam(rs));
        }
        return teams;
    }

    private Team buildTeam(final ResultSet rs) throws SQLException {
        Team team = new Team();
        logger.debug("Building Team object.");
        team.setId(rs.getLong("id"));
        team.setName(rs.getString("name"));
        team.setLogoFile(rs.getString("logo_file"));
        Country c = new Country();
        c.setId(rs.getLong("country_id"));
        team.setCountry(c);
        var creatorID = rs.getLong("creator");
        if (creatorID > 0) {
            Player creator = new Player();
            creator.setId(creatorID);
            team.setCreator(creator);
        }
        var captainID = rs.getLong("captain");
        if (captainID > 0) {
            Player captain = new Player();
            captain.setId(captainID);
            team.setCaptain(captain);
        }
        var coachID = rs.getLong("coach");
        if (coachID != 0) {
            Player coach = new Player();
            coach.setId(coachID);
            team.setCoach(coach);
        }
        Game game = new Game();
        game.setId(rs.getLong("game"));
        team.setGame(game);
        team.setOverview(rs.getString("overview"));
        logger.debug("'Team' object was build successfully.");
        return team;
    }

    private void setUpStatement(final PreparedStatement st, final Team team)
            throws SQLException {
        st.setString(TEAM_NAME, team.getName());
        st.setString(LOGO_FILE, team.getLogoFile());
        st.setLong(COUNTRY_ID, team.getCountry().getId());
        if (team.getCreator() != null) {
            st.setLong(CREATOR_ID, team.getCreator().getId());
        } else {
            st.setNull(CREATOR_ID, 0);
        }
        if (team.getCaptain() != null) {
            st.setLong(CAPTAIN_ID, team.getCaptain().getId());
        } else {
            st.setNull(CAPTAIN_ID, 0);
        }
        if (team.getCoach() != null) {
            st.setLong(COACH_ID, team.getCoach().getId());
        } else {
            st.setNull(COACH_ID, 0);
        }
        st.setLong(GAME_ID, team.getGame().getId());
        st.setString(OVERVIEW, team.getOverview());
    }
}
