package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.bean.Tournament;
import by.koshko.cyberwikia.bean.TournamentTeam;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.TournamentTeamDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class TournamentTeamDaoImpl extends AbstractDao implements TournamentTeamDao {

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
              + " WHERE tournament_id=?;";
    private static final String FIND_PLAYER_TOURNAMENTS
            = "SELECT tournament_id, m2m_tournament_team.team_id, placement"
              + " FROM m2m_tournament_team"
              + " JOIN m2m_player_team m2mpt"
              + " ON m2m_tournament_team.team_id = m2mpt.team_id"
              + " JOIN tournament t on tournament_id = t.id"
              + " WHERE m2mpt.player_id=?"
              + " AND IF(ISNULL(m2mpt.leave_date), true, leave_date >= end_date)"
              + " AND m2mpt.join_date <= t.start_date;";
    public static final String SAVE
            = "INSERT INTO m2m_tournament_team"
              + " (tournament_id, team_id, placement)"
              + " VALUES (?, ?, ?);";
    public static final String UPDATE
            = "UPDATE m2m_tournament_team"
              + " SET placement=?"
              + " WHERE tournament_id=? AND team_id=?;";
    public static final String DELETE
            = "DELETE FROM m2m_tournament_team"
              + " WHERE tournament_id=? AND team_id=?;";

    @Override
    public List<TournamentTeam> findTournamentTeam(final Team team)
            throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(FIND_TOURNAMENTS)) {
            statement.setLong(1, team.getId());
            try (ResultSet rs = statement.executeQuery()) {
                return buildMultipleInstances(rs, team);
            }
        } catch (SQLException e) {
            logger.error("Error while processing SQL query."
                         + " SQL state: {}. SQL message: {}.",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot find team tournaments.");
        }
    }

    @Override
    public List<TournamentTeam> findTournamentTeam(final Tournament tournament)
            throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(FIND_TEAMS)) {
            statement.setLong(1, tournament.getId());
            try (ResultSet rs = statement.executeQuery()) {
                return buildMultipleInstances(rs, tournament);
            }
        } catch (SQLException e) {
            logger.error("Error while processing SQL query."
                         + " SQL state: {}. SQL message: {}.",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot find tournament teams.");
        }
    }

    @Override
    public List<TournamentTeam> findTournamentTeam(final Player player)
            throws DaoException {
        try (PreparedStatement statement
                 = getConnection().prepareStatement(FIND_PLAYER_TOURNAMENTS)) {
            statement.setLong(1, player.getId());
            try (ResultSet rs = statement.executeQuery()) {
                return buildMultipleInstances(rs);
            }
        } catch (SQLException e) {
            logger.error("Error while processing SQL query."
                         + " SQL state: {}. SQL message: {}.",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot find tournaments.");
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
    public void save(final TournamentTeam entity) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(SAVE)) {
            setUpStatement(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while processing SQL query."
                         + " SQL state: {}. SQL message: {}.",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot save 'TournamentTeam' entity.");
        }
    }

    @Override
    public void update(final TournamentTeam entity) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(UPDATE)) {
            int index = 1;
            statement.setInt(index++, entity.getPlacement());
            statement.setLong(index++, entity.getTournament().getId());
            statement.setLong(index, entity.getTeam().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while processing SQL query."
                         + " SQL state: {}. SQL message: {}.",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot update 'TournamentTeam' entity.");
        }
    }

    @Override
    public void delete(final TournamentTeam entity) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(DELETE)) {
            statement.setLong(1, entity.getTournament().getId());
            statement.setLong(2, entity.getTeam().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while processing SQL query."
                         + " SQL state: {}. SQL message: {}.",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot delete 'TournamentTeam' entity.");
        }
    }

    private void setUpStatement(final PreparedStatement statement,
                                final TournamentTeam entity)
            throws SQLException {
        int index = 1;
        statement.setLong(index++, entity.getTournament().getId());
        statement.setLong(index++, entity.getTeam().getId());
        statement.setInt(index, entity.getPlacement());
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
                                                        final Tournament tournament)
            throws SQLException {
        List<TournamentTeam> tournaments = new ArrayList<>();
        while (rs.next()) {
            tournaments.add(buildTournamentTeam(rs, tournament));
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
        return build(rs, tournamentTeam);
    }

    private TournamentTeam buildTournamentTeam(final ResultSet rs,
                                               final Team team)
            throws SQLException {
        TournamentTeam tournamentTeam = new TournamentTeam();
        tournamentTeam.setTeam(team);
        Tournament tournament = new Tournament();
        tournament.setId(rs.getLong(TOURNAMENT_ID));
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
