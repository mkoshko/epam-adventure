package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.TeamDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class TeamDaoImpl extends AbstractDao implements TeamDao {
    //TODO Fix this shit.
    private static final String SELECT = "SELECT team.id, team.name, logo_file, "
            + "c.name as country, "
            + "p.nickname as creator, "
            + "p1.nickname as captain, "
            + "p2.nickname as coach, "
            + "g.title as game, "
            + "team.overview "
            + "FROM team "
            + "LEFT JOIN country c on team.country_id = c.id "
            + "LEFT JOIN player p on team.captain = p.id "
            + "LEFT JOIN player p1 on team.creator = p1.id "
            + "LEFT JOIN player p2 on team.coach = p2.id "
            + "LEFT JOIN game g on team.game = g.id";
    private static final String FIND_BY_NAME_QUERY = SELECT + " WHERE team.name=?";
    private static final String GET_QUERY = SELECT + " WHERE team.id=?;";
    private static final String GET_ALL_QUERY = SELECT + ";";
    private static final String UPDATE_QUERY = "UPDATE team "
            + "SET name=?, logo_file=?, "
            + "country_id=(SELECT country.id FROM country WHERE country.name=?),"
            + " creator=(SELECT player.id FROM player WHERE player.nickname=?),"
            + " captain=(SELECT player.id FROM player WHERE player.nickname=?),"
            + " coach=(SELECT player.id FROM player WHERE player.nickname=?),"
            + " game=(SELECT game.id FROM game WHERE game.title=?),"
            + " overview=? WHERE team.id=?";
    private static final String DELETE_QUERY = "DELETE team FROM team WHERE id=?";
    private static final String SAVE_QUERY = "INSERT INTO team (name, logo_file, country_id, creator,"
            + " captain, coach, game, overview)"
            + " VALUES (?, ?,"
            + " (SELECT country.id FROM country WHERE country.name=?),"
            + " (SELECT player.id FROM player WHERE player.nickname=?),"
            + " (SELECT player.id FROM player WHERE player.nickname=?),"
            + " (SELECT player.id FROM player WHERE player.nickname=?),"
            + " (SELECT game.id FROM game WHERE game.title=?), ?);";
    private static final String REMOVE_PLAYER_QUERY = "DELETE m2m_player_team FROM m2m_player_team" +
            " WHERE player_id=? AND team_id=?";
    private static final String ADD_PLAYER_QUERY = "INSERT INTO m2m_player_team (player_id, team_id, active, join_date)" +
            " VALUES (?, ?, 1, ?)";
    @Override
    public Optional<Team> findByName(final String name) throws DaoException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(FIND_BY_NAME_QUERY);
            statement.setString(1, name);
            rs = statement.executeQuery();
            return Optional.ofNullable(buildSingleInstance(rs));
        } catch (SQLException e) {
            logger.error("Cannot find team. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot find team by name in database "
                    + "due to internal errors.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public Optional<Team> get(final long id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(GET_QUERY);
            statement.setLong(1, id);
            rs = statement.executeQuery();
            return Optional.ofNullable(buildSingleInstance(rs));
        } catch (SQLException e) {
            logger.error("Cannot find team. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot find team by id in database "
                    + "due to internal errors.");
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
            logger.error("Cannot fetch teams. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot fetch all teams from database "
                    + "due to internal errors.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public void save(final Team entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(SAVE_QUERY);
            setUpStatement(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot save team. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot save team to database "
                    + "due to internal errors.");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void update(final Team entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(UPDATE_QUERY);
            setUpStatement(statement, entity);
            statement.setLong(9, entity.getId());
            if (statement.executeUpdate() == 1) {
                logger.info("Team '{}' was successfully updated", entity.getName());
            }
        } catch (SQLException e) {
            logger.error("Cannot update team. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot update team in database "
                    + "due to internal errors.");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void delete(final Team entity) throws DaoException {
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
            throw new DaoException("Cannot remove team from database "
                    + "due to internal errors.");
        } finally {
            closeStatement(statement);
        }
    }

    public void removePlayer(final Player player, final Team team) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(REMOVE_PLAYER_QUERY);
            statement.setLong(1, player.getId());
            statement.setLong(2, team.getId());
            if ( statement.executeUpdate() == 1){
                logger.info("Player {} was successfully removed from team {}.", player.getNickname(), team.getName());
            }
        } catch (SQLException e) {
            logger.error("Cannot remove player from team. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot remove player from team "
                    + "due to internal errors.");
        } finally {
            closeStatement(statement);
        }
    }

    public void addPlayer(final Player player, final Team team) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(ADD_PLAYER_QUERY);
            statement.setLong(1, player.getId());
            statement.setLong(2, team.getId());
            statement.setString(3, LocalDate.now().toString());
            if (statement.executeUpdate() == 1) {
                logger.info("Player {} was successfully added to team {}.", player.getNickname(), team.getName());
            }
        } catch (SQLException e) {
            logger.error("Cannot add player to team. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot add player to team "
                    + "due to internal errors.");
        } finally {
            closeStatement(statement);
        }
    }

    public void disbandPlayer(final Player player, final Team team) throws DaoException {
        String DISBAND_QUERY = "UPDATE m2m_player_team SET active=0, leave_date=?" +
                " WHERE player_id=? AND team_id=?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(DISBAND_QUERY);
            statement.setString(1, LocalDate.now().toString());
            statement.setLong(2, player.getId());
            statement.setLong(3, team.getId());
            if (statement.executeUpdate() == 1) {
                logger.info("Player {} was disband from team {}", player.getNickname(), team.getName());
            }
        } catch (SQLException e) {
            logger.error("Cannot disband player from team. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot disband player from team "
                    + "due to internal errors.");
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
        logger.debug("Building 'Team' object...");
        team.setId(rs.getLong("id"));
        team.setName(rs.getString("name"));
        team.setLogoFile(rs.getString("logo_file"));
        team.setLocation(rs.getString("country"));
        team.setCreator(rs.getString("creator"));
        team.setCaptain(rs.getString("captain"));
        team.setCoach(rs.getString("coach"));
        team.setGame(rs.getString("game"));
        team.setOverview(rs.getString("overview"));
        logger.debug("'Team' object was build successfully.");
        return team;
    }

    private void setUpStatement(final PreparedStatement st, final Team team)
            throws SQLException {
        st.setString(1, team.getName());
        st.setString(2, team.getLogoFile());
        st.setString(3, team.getLocation());
        st.setString(4, team.getCreator());
        st.setString(5, team.getCaptain());
        st.setString(6, team.getCoach());
        st.setString(7, team.getGame());
        st.setString(8, team.getOverview());
    }
}
