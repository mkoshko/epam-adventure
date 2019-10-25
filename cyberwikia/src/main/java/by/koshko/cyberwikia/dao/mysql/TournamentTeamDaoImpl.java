package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.bean.Tournament;
import by.koshko.cyberwikia.bean.TournamentTeam;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.TournamentTeamDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class TournamentTeamDaoImpl extends AbstractDao
        implements TournamentTeamDao {

    private static final Logger LOGGER
            = LogManager.getLogger();

    private static final String TOURNAMENT_ID = "tournament_id";
    private static final String TEAM_ID = "team_id";
    private static final String TEAM_PLACEMENT = "placement";

    private static final String FIND_TOURNAMENTS
            = "SELECT tournament_id, team_id, placement"
              + " FROM m2m_tournament_team"
              + " WHERE team_id=?;";
    private static final String FIND_TEAMS
            = "SELECT tournament_id, team_id, placement"
              + " FROM m2m_tournament_team"
              + " WHERE tournament_id=?"
              + " ORDER BY placement;";
    private static final String FIND_PLAYER_TOURNAMENTS
            = "SELECT tournament_id, m2m_tournament_team.team_id, placement"
              + " FROM m2m_tournament_team"
              + " JOIN m2m_player_team m2mpt"
              + " ON m2m_tournament_team.team_id = m2mpt.team_id"
              + " JOIN tournament t on tournament_id = t.id"
              + " WHERE m2mpt.player_id=? "
              + "AND IF(ISNULL(m2mpt.leave_date), true, leave_date >= end_date)"
              + " AND m2mpt.join_date <= t.start_date;";
    private static final String SAVE
            = "INSERT INTO m2m_tournament_team"
              + " (tournament_id, team_id, placement)"
              + " VALUES (?, ?, ?);";
    private static final String UPDATE
            = "UPDATE m2m_tournament_team"
              + " SET placement=?"
              + " WHERE tournament_id=? AND team_id=?;";
    private static final String DELETE
            = "DELETE FROM m2m_tournament_team"
              + " WHERE tournament_id=? AND team_id=?;";
    private static final String FIND_TOP_TEAMS
            = "SELECT team_id, AVG(placement) as avg FROM m2m_tournament_team"
              + " GROUP BY team_id ORDER BY avg LIMIT ?;";

    public TournamentTeamDaoImpl(final Connection newConnection) {
        super(newConnection);
    }

    @Override
    public List<Long> getTopTeams(final int limit) throws DaoException {
        try (PreparedStatement statement
                = getConnection().prepareStatement(FIND_TOP_TEAMS)) {
            statement.setInt(1, limit);
            ResultSet rs = statement.executeQuery();
            ArrayList<Long> teamsId = new ArrayList<>();
            while (rs.next()) {
                teamsId.add(rs.getLong(1));
            }
            return teamsId;
        } catch (SQLException e) {
            throw new DaoException("Cannot get top teams.", e);
        }
    }

    @Override
    public List<TournamentTeam> findTournamentTeam(final Team team)
            throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(FIND_TOURNAMENTS)) {
            statement.setLong(1, team.getId());
            return buildMultipleInstances(statement.executeQuery(), team);
        } catch (SQLException e) {
            throw new DaoException("Cannot find team tournaments.", e);
        }
    }

    @Override
    public List<TournamentTeam> findTournamentTeam(final Tournament tournament)
            throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(FIND_TEAMS)) {
            statement.setLong(1, tournament.getId());
            return buildMultipleInstances(statement.executeQuery(), tournament);
        } catch (SQLException e) {
            throw new DaoException("Cannot find tournament teams.", e);
        }
    }

    @Override
    public List<TournamentTeam> findTournamentTeam(final Player player)
            throws DaoException {
        try (PreparedStatement statement = getConnection()
                .prepareStatement(FIND_PLAYER_TOURNAMENTS)) {
            statement.setLong(1, player.getId());
            return buildMultipleInstances(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException("Cannot find player tournaments.", e);
        }
    }

    @Override
    public TournamentTeam get(final long id) throws DaoException {
        throw new DaoException("Unsupported operation.");
    }

    @Override
    public List<TournamentTeam> getAll() throws DaoException {
        throw new DaoException("Unsupported operation.");
    }

    @Override
    public boolean save(final TournamentTeam entity) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(SAVE)) {
            setUpStatement(statement, entity);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            return !isDuplicateError(e, "Cannot save TournamentTeam entity.");
        }
    }

    @Override
    public boolean update(final TournamentTeam entity) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(UPDATE)) {
            statement.setInt(1, entity.getPlacement());
            statement.setLong(2, entity.getTournament().getId());
            statement.setLong(3, entity.getTeam().getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            return !isDuplicateError(e, "Cannot update TournamentTeam entity.");
        }
    }

    @Override
    public boolean delete(final TournamentTeam entity) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(DELETE)) {
            statement.setLong(1, entity.getTournament().getId());
            statement.setLong(2, entity.getTeam().getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("Cannot delete TournamentTeam entity.", e);
        }
    }

    private void setUpStatement(final PreparedStatement statement,
                                final TournamentTeam entity)
            throws SQLException {
        statement.setLong(1, entity.getTournament().getId());
        statement.setLong(2, entity.getTeam().getId());
        statement.setInt(3, entity.getPlacement());
    }

    private List<TournamentTeam> buildMultipleInstances(final ResultSet rs)
            throws SQLException {
        List<TournamentTeam> tournaments = new ArrayList<>();
        while (rs.next()) {
            tournaments.add(buildTournamentTeam(rs));
        }
        return tournaments;
    }

    private List<TournamentTeam> buildMultipleInstances(final ResultSet rs,
                                                        final Team team)
            throws SQLException {
        List<TournamentTeam> tournaments = new ArrayList<>();
        while (rs.next()) {
            tournaments.add(buildTournamentTeam(rs, team));
        }
        return tournaments;
    }

    private List<TournamentTeam> buildMultipleInstances(final ResultSet rs,
                                                        final Tournament entity)
            throws SQLException {
        List<TournamentTeam> tournaments = new ArrayList<>();
        while (rs.next()) {
            tournaments.add(buildTournamentTeam(rs, entity));
        }
        return tournaments;
    }

    private TournamentTeam buildTournamentTeam(final ResultSet rs,
                                               final Tournament tournament)
            throws SQLException {
        TournamentTeam tournamentTeam = new TournamentTeam();
        tournamentTeam.setTournament(tournament);
        Team team = new Team();
        team.setId(rs.getLong(TEAM_ID));
        tournamentTeam.setTeam(team);
        return build(rs, tournamentTeam);
    }

    private TournamentTeam buildTournamentTeam(final ResultSet rs,
                                               final Team team)
            throws SQLException {
        TournamentTeam tournamentTeam = new TournamentTeam();
        tournamentTeam.setTeam(team);
        Tournament tournament = new Tournament();
        tournament.setId(rs.getLong(TOURNAMENT_ID));
        tournamentTeam.setTournament(tournament);
        return build(rs, tournamentTeam);
    }

    private TournamentTeam buildTournamentTeam(final ResultSet rs)
            throws SQLException {
        TournamentTeam tournamentTeam = new TournamentTeam();
        Team team = new Team();
        team.setId(rs.getLong(TEAM_ID));
        tournamentTeam.setTeam(team);
        Tournament tournament = new Tournament();
        tournament.setId(rs.getLong(TOURNAMENT_ID));
        tournamentTeam.setTournament(tournament);
        return build(rs, tournamentTeam);
    }

    private TournamentTeam build(final ResultSet rs,
                                 final TournamentTeam entity)
            throws SQLException {
        entity.setPlacement(rs.getInt(TEAM_PLACEMENT));
        return entity;
    }
}
