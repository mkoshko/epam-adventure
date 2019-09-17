package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.TeamDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public Optional<Team> findByName(final String name) throws DaoException {
        String query = SELECT + " WHERE team.name=?";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(query);
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
        String query = SELECT + " WHERE team.id=?;";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(query);
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
        String query = SELECT + ";";
        System.out.println(query);
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(query);
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
        String query = "INSERT INTO team (name, logo_file, country_id, creator,"
                + " captain, coach, game, overview)"
                + " VALUES (?, ?,"
                + " (SELECT country.id FROM country WHERE country.name=?),"
                + " (SELECT player.id FROM player WHERE player.nickname=?),"
                + " (SELECT player.id FROM player WHERE player.nickname=?),"
                + " (SELECT player.id FROM player WHERE player.nickname=?),"
                + " (SELECT game.id FROM game WHERE game.title=?), ?);";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
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
        String query = "UPDATE team "
           + "SET name=?, logo_file=?, "
           + "country_id=(SELECT country.id FROM country WHERE country.name=?),"
           + " creator=(SELECT player.id FROM player WHERE player.nickname=?),"
           + " captain=(SELECT player.id FROM player WHERE player.nickname=?),"
           + " coach=(SELECT player.id FROM player WHERE player.nickname=?),"
           + " game=(SELECT game.id FROM game WHERE game.title=?),"
           + " overview=? WHERE team.id=?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
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
        String query = "DELETE team FROM team WHERE id=?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);
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
