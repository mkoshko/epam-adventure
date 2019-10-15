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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class TeamDaoImpl extends AbstractDao implements TeamDao {
    private static final String FIND_BY_NAME
            = "SELECT id, name, logo_file, country_id, creator, captain, coach,"
              + " game, overview FROM team WHERE name=?;";
    private static final String GET
            = "SELECT id, name, logo_file, country_id, creator, captain, coach,"
              + " game, overview FROM team WHERE id=?;";
    private static final String GET_ALL
            = "SELECT id, name, logo_file, country_id, creator, captain, coach,"
              + " game, overview FROM team;";
    public static final String GET_ROWS
            = "SELECT COUNT(*) FROM team;";
    public static final String GET_ALL_LIMIT
            = "SELECT id, name, logo_file, country_id, creator, captain, coach,"
              + " game, overview FROM team"
              + " LIMIT ?, ?;";
    private static final String UPDATE
            = "UPDATE team SET name=?, logo_file=?, country_id=?, creator=?, "
              + "captain=?, coach=?, game=?, overview=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM team WHERE id=?";
    private static final String SAVE
            = "INSERT INTO team (name, logo_file, country_id, creator,"
              + " captain, coach, game, overview)"
              + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_CREATED_TEAM
            = "SELECT id, name, logo_file, country_id, creator, captain, coach,"
              + " game, overview FROM team WHERE creator=?;";

    public int getRows() throws DaoException {
        try (Statement statement = getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(GET_ROWS);
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new DaoException("Cannot get number of records.", e);
        }
    }

    public Team findCreatedTeam(final long playerId) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(FIND_CREATED_TEAM)) {
            statement.setLong(1, playerId);
            return buildSingleInstance(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException("Cannot find players created team.", e);
        }
    }

    @Override
    public Team findByName(final String name) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(FIND_BY_NAME)) {
            statement.setString(1, name);
            return buildSingleInstance(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException("Cannot find team by name.", e);
        }
    }

    @Override
    public Team get(final long id) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(GET)) {
            statement.setLong(1, id);
            return buildSingleInstance(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException("Cannot find team by ID.", e);
        }
    }

    @Override
    public List<Team> getAll(final int offset, final int limit)
            throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(GET_ALL_LIMIT)) {
            statement.setInt(1, offset);
            statement.setInt(2, limit);
            return buildMultipleInstances(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException("Cannot get team list.", e);
        }
    }

    @Override
    public List<Team> getAll() throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(GET_ALL)) {
            return buildMultipleInstances(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException("Cannot get team list.", e);
        }
    }

    @Override
    public boolean save(final Team entity) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(SAVE)) {
            setUpStatement(statement, entity);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            return !isDuplicateError(e, "Cannot save team.");
        }
    }

    @Override
    public boolean update(final Team entity) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(UPDATE)) {
            setUpStatement(statement, entity);
            statement.setLong(9, entity.getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            return !isDuplicateError(e, "Cannot update team.");
        }
    }

    @Override
    public boolean delete(final Team entity) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(DELETE)) {
            statement.setLong(1, entity.getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("Cannot delete team.", e);
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
        if (coachID > 0) {
            Player coach = new Player();
            coach.setId(coachID);
            team.setCoach(coach);
        }
        Game game = new Game();
        game.setId(rs.getLong("game"));
        team.setGame(game);
        team.setOverview(rs.getString("overview"));
        return team;
    }

    private void setUpStatement(final PreparedStatement st, final Team team)
            throws SQLException {
        int index = 1;
        st.setString(index++, team.getName());
        st.setString(index++, team.getLogoFile());
        st.setLong(index++, team.getCountry().getId());
        if (team.getCreator() != null) {
            st.setLong(index++, team.getCreator().getId());
        } else {
            st.setNull(index++, 0);
        }
        if (team.getCaptain() != null) {
            st.setLong(index++, team.getCaptain().getId());
        } else {
            st.setNull(index++, 0);
        }
        if (team.getCoach() != null) {
            st.setLong(index++, team.getCoach().getId());
        } else {
            st.setNull(index++, 0);
        }
        st.setLong(index++, team.getGame().getId());
        st.setString(index, team.getOverview());
    }
}
