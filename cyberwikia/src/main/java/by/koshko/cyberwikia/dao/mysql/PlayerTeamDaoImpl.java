package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.PlayerTeam;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.PlayerTeamDao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class PlayerTeamDaoImpl extends AbstractDao implements PlayerTeamDao {

    private static final String FIND_PLAYERS
            = "SELECT team_id, active, join_date, leave_date "
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

    @Override
    public List<PlayerTeam> findPlayerTeam(Team team) throws DaoException {
        requireNonNullEntity(team);
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(FIND_PLAYERS);
            statement.setLong(1, team.getId());
            rs = statement.executeQuery();
            List<PlayerTeam> playerTeams = buildMultipleInstances(rs, team);
            return playerTeams;
        } catch (SQLException e) {
            //TODO handle sql exception
            throw new DaoException("");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public List<PlayerTeam> findPlayerTeam(Player player) throws DaoException {
        requireNonNullEntity(player);
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(FIND_TEAMS);
            statement.setLong(1, player.getId());
            rs = statement.executeQuery();
            List<PlayerTeam> playerTeams = buildMultipleInstances(rs, player);
//            playerTeams.forEach(playerTeam -> playerTeam.setPlayer(player));
            return playerTeams;
        } catch (SQLException e) {
            logger.error("Cannot find players teams. SQL state: {}."
                    + " Message: {}", e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot find players teams.");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public Optional<PlayerTeam> get(long id) throws DaoException {
        throw new DaoException("Unsupported operation.");
    }

    @Override
    public List<PlayerTeam> getAll() throws DaoException {
        throw new DaoException("Unsupported operation");
    }

    @Override
    public void save(PlayerTeam entity) throws DaoException {
        requireNonNullEntity(entity);
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(SAVE);
            setUpStatement(statement, entity);
            if (statement.executeUpdate() == 1) {
                //TODO logger
            }
        } catch (SQLException e) {
            logger.error("Cannot put player into team. SQL state: {}."
                    + " Message: {}", e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot put player into team.");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void update(PlayerTeam entity) throws DaoException {
        requireNonNullEntity(entity);
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(UPDATE);
            setUpStatement(statement, entity);
            statement.setLong(6, entity.getPlayer().getId());
            statement.setLong(7, entity.getTeam().getId());
            if (statement.executeUpdate() == 1) {
                //TODO logger
            }
        } catch (SQLException e) {
            throw new DaoException("");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void delete(PlayerTeam entity) throws DaoException {
        requireNonNullEntity(entity);
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(DELETE);
            statement.setLong(1, entity.getPlayer().getId());
            statement.setLong(2, entity.getTeam().getId());
            if (statement.executeUpdate() == 1) {
                //TODO log
            }
        } catch (SQLException e) {
            throw new DaoException("");
        } finally {
            closeStatement(statement);
        }
    }

    private List<PlayerTeam> buildMultipleInstances(ResultSet rs, Player player) throws SQLException {
        List<PlayerTeam> playerTeams = new ArrayList<>();
        while (rs.next()) {
            playerTeams.add(buildPlayerTeam(rs, player));
        }
        return playerTeams;
    }


    private List<PlayerTeam> buildMultipleInstances(ResultSet rs, Team team) throws SQLException {
        List<PlayerTeam> playerTeams = new ArrayList<>();
        while (rs.next()) {
            playerTeams.add(buildPlayerTeam(rs, team));
        }
        return playerTeams;
    }

    private PlayerTeam buildPlayerTeam(ResultSet rs, Player player) throws SQLException {
        PlayerTeam playerTeam = new PlayerTeam();
        playerTeam.setPlayer(player);
        Team team = new Team();
        team.setId(rs.getLong("team_id"));
        playerTeam.setTeam(team);
        return build(rs, playerTeam);
    }

    private PlayerTeam buildPlayerTeam(ResultSet rs, Team team) throws SQLException {
        PlayerTeam playerTeam = new PlayerTeam();
        playerTeam.setTeam(team);
        Player player = new Player();
        player.setId(rs.getLong("player_id"));
        playerTeam.setPlayer(player);
        return build(rs, playerTeam);
    }

    private PlayerTeam build(ResultSet rs, PlayerTeam playerTeam) throws SQLException {
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
        st.setLong(1, pt.getPlayer().getId());
        st.setLong(2, pt.getTeam().getId());
        st.setBoolean(3, pt.isActive());
        st.setDate(4, Date.valueOf(pt.getJoinDate()));
        if (pt.getLeaveDate() != null) {
            st.setDate(5, Date.valueOf(pt.getLeaveDate()));
        } else {
            //TODO check sql type
            st.setNull(5, 0);
        }
    }
}
