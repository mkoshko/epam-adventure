package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.PlayerTeam;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.PlayerTeamDao;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class PlayerTeamDaoImpl extends AbstractDao implements PlayerTeamDao {

    private static final String FIND_PLAYERS
            = "SELECT player_id, active, join_date, leave_date "
              + "FROM m2m_player_team WHERE team_id=?";
    private static final String SAVE
            = "INSERT INTO m2m_player_team (player_id, team_id, active, join_date, leave_date) "
              + "VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE
            = "UPDATE m2m_player_team SET player_id=?, team_id=?,"
              + " active=?, join_date=?, leave_date=? WHERE player_id=? AND team_id=?;";
    private static final String DELETE
            = "DELETE FROM m2m_player_team WHERE player_id=? AND team_id=?";
    private static final String FIND_TEAMS
            = "SELECT team_id, active, join_date, leave_date "
              + "FROM m2m_player_team WHERE player_id=?";
    private static final String FIND_ACTIVE_TEAM
            = "SELECT team_id, active, join_date, leave_date "
              + "FROM m2m_player_team "
              + "WHERE player_id=? AND active=1;";
    private static final String FIND_PLAYER_TEAM
            = "SELECT player_id, team_id, active, join_date, leave_date "
              + "FROM m2m_player_team "
              + "WHERE player_id=? AND team_id=?;";
    private static final String ACTIVE_TEAM_ID
            = "{CALL active_team_id(?)}";

    //TODO rethink method implementation.
    @Override
    public long isActiveTeamPlayer(final long playerId) throws DaoException {
        try (CallableStatement statement
                     = getConnection().prepareCall(ACTIVE_TEAM_ID)) {
            statement.setLong(1, playerId);
            statement.execute();
            return statement.getLong(1);
        } catch (SQLException e) {
            throw new DaoException("Cannot get player active team id", e);
        }
    }

    @Override
    public List<PlayerTeam> findPlayerTeam(final Team team) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(FIND_PLAYERS)) {
            statement.setLong(1, team.getId());
            return buildMultipleInstances(statement.executeQuery(), team);
        } catch (SQLException e) {
            throw new DaoException("Cannot find team players.", e);
        }
    }

    public PlayerTeam findPlayerTeam(final long playerId, final long teamId)
            throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(FIND_PLAYER_TEAM)) {
            statement.setLong(1, playerId);
            statement.setLong(2, teamId);
            return buildSingleInstance(statement.executeQuery(),
                    playerId, teamId);
        } catch (SQLException e) {
            throw new DaoException("Cannot find player_team record.", e);
        }
    }

    @Override
    public List<PlayerTeam> findPlayerTeam(final Player player)
            throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(FIND_TEAMS)) {
            statement.setLong(1, player.getId());
            return buildMultipleInstances(statement.executeQuery(), player);
        } catch (SQLException e) {
            throw new DaoException("Cannot find player teams.", e);
        }
    }

    public PlayerTeam findPlayerTeamActive(final Player player)
            throws DaoException {
        try (PreparedStatement statement
                = getConnection().prepareStatement(FIND_ACTIVE_TEAM)) {
            statement.setLong(1, player.getId());
            return buildSingleInstance(statement.executeQuery(), player);
        } catch (SQLException e) {
            throw new DaoException("Cannot find player active team.", e);
        }
    }

    @Override
    public PlayerTeam get(final long id) throws DaoException {
        throw new DaoException("Unsupported operation.");
    }

    @Override
    public List<PlayerTeam> getAll() throws DaoException {
        throw new DaoException("Unsupported operation");
    }

    @Override
    public boolean save(final PlayerTeam entity) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(SAVE)) {
            setUpStatement(statement, entity);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            return !isDuplicateError(e, "Cannot put player into team.");
        }
    }

    @Override
    public boolean update(final PlayerTeam entity) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(UPDATE)) {
            setUpStatement(statement, entity);
            statement.setLong(6, entity.getPlayer().getId());
            statement.setLong(7, entity.getTeam().getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            return !isDuplicateError(e, "Cannot update player_team record.");
        }
    }

    @Override
    public boolean delete(final PlayerTeam entity) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(DELETE)) {
            statement.setLong(1, entity.getPlayer().getId());
            statement.setLong(2, entity.getTeam().getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("Cannot delete player_team record.", e);
        }
    }

    private PlayerTeam buildSingleInstance(final ResultSet rs,
                                           final long playerId,
                                           final long teamId)
            throws SQLException {
        if (rs.next()) {
            PlayerTeam playerTeam = new PlayerTeam();
            Player player = new Player();
            player.setId(playerId);
            Team team = new Team();
            team.setId(teamId);
            playerTeam.setPlayer(player);
            playerTeam.setTeam(team);
            return build(rs, playerTeam);
        }
        return null;
    }

    private PlayerTeam buildSingleInstance(final ResultSet rs, final Player player)
            throws SQLException {
        if (rs.next()) {
            return buildPlayerTeam(rs, player);
        } else {
            return null;
        }
    }

    private List<PlayerTeam> buildMultipleInstances(final ResultSet rs, final Player player) throws SQLException {
        List<PlayerTeam> playerTeams = new ArrayList<>();
        while (rs.next()) {
            playerTeams.add(buildPlayerTeam(rs, player));
        }
        return playerTeams;
    }


    private List<PlayerTeam> buildMultipleInstances(final ResultSet rs, final Team team) throws SQLException {
        List<PlayerTeam> playerTeams = new ArrayList<>();
        while (rs.next()) {
            playerTeams.add(buildPlayerTeam(rs, team));
        }
        return playerTeams;
    }

    private PlayerTeam buildPlayerTeam(final ResultSet rs,
                                       final Player player) throws SQLException {
        PlayerTeam playerTeam = new PlayerTeam();
        playerTeam.setPlayer(player);
        Team team = new Team();
        team.setId(rs.getLong("team_id"));
        playerTeam.setTeam(team);
        return build(rs, playerTeam);
    }

    private PlayerTeam buildPlayerTeam(final ResultSet rs,
                                       final Team team) throws SQLException {
        PlayerTeam playerTeam = new PlayerTeam();
        playerTeam.setTeam(team);
        Player player = new Player();
        player.setId(rs.getLong("player_id"));
        playerTeam.setPlayer(player);
        return build(rs, playerTeam);
    }

    private PlayerTeam build(final ResultSet rs,
                             final PlayerTeam playerTeam) throws SQLException {
        playerTeam.setJoinDate(LocalDate.parse(rs.getString("join_date")));
        String leaveDate = rs.getString("leave_date");
        if (leaveDate != null) {
            playerTeam.setLeaveDate(LocalDate.parse(leaveDate));
        }
        playerTeam.setActive(rs.getBoolean("active"));
        return playerTeam;
    }

    private void setUpStatement(final PreparedStatement st, final PlayerTeam pt)
            throws SQLException {
        int index = 1;
        st.setLong(index++, pt.getPlayer().getId());
        st.setLong(index++, pt.getTeam().getId());
        st.setBoolean(index++, pt.isActive());
        st.setDate(index++, Date.valueOf(pt.getJoinDate()));
        if (pt.getLeaveDate() != null) {
            st.setDate(index, Date.valueOf(pt.getLeaveDate()));
        } else {
            st.setNull(index, Types.NULL);
        }
    }
}
