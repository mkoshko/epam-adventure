package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Tournament;
import by.koshko.cyberwikia.bean.TournamentTier;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.TournamentDao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class TournamentDaoImpl extends AbstractDao implements TournamentDao {

    private static final String GET
            = "SELECT id, name, tier, prize, overview, start_date, end_date"
              + " FROM tournament WHERE id=?;";
    private static final String GET_ALL
            = "SELECT id, name, tier, prize, overview, start_date, end_date"
              + " FROM tournament;";
    private static final String SAVE
            = "INSERT INTO tournament"
              + " (name, tier, prize, overview, start_date, end_date)"
              + " VALUES (?,?,?,?,?,?)";
    private static final String UPDATE
            = "UPDATE tournament SET name=?, tier=?, prize=?, overview=?,"
              + "start_date=?, end_date=? WHERE id=?;";
    private static final String DELETE
            = "DELETE FROM tournament WHERE id=?;";

    @Override
    public Optional<Tournament> get(final long id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(GET);
            rs = statement.executeQuery();
            statement.setLong(1, id);
            return Optional.ofNullable(buildSingleInstance(rs));
        } catch (SQLException e) {
            logger.error("Cannot fetch tournament. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot fetch tournament.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public List<Tournament> getAll() throws DaoException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(GET_ALL);
            rs = statement.executeQuery();
            return buildMultipleInstances(rs);
        } catch (SQLException e) {
            logger.error("Cannot fetch tournaments. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot fetch tournaments.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public void save(final Tournament entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(SAVE);
            setUpStatement(statement, entity);
            if (statement.executeUpdate() == 1) {
                logger.info("Tournament '{}' has been saved.", entity.getName());
            }
        } catch (SQLException e) {
            logger.error("Cannot save team. SQL state: {}. Message: {}",
                    e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot save team.");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void update(final Tournament entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(UPDATE);
            setUpStatement(statement, entity);
            statement.setLong(7, entity.getId());
            if (statement.executeUpdate() == 1) {
                logger.info("Tournament '{}' has been updated.", entity.getName());
            }
        } catch (SQLException e) {
            logger.error("Cannot update team '{}'. SQL state: {}. Message: {}",
                    entity.getName(), e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot save team.");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void delete(final Tournament entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(DELETE);
            statement.setLong(1, entity.getId());
            if (statement.executeUpdate() == 1) {
                logger.info("Tournament '{}' has been removed.", entity.getName());
            }
        } catch (SQLException e) {
            logger.error("Cannot delete team '{}'. SQL state: {}. Message: {}",
                    entity.getName(), e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot delete team.");
        } finally {
            closeStatement(statement);
        }
    }

    private Tournament buildSingleInstance(final ResultSet rs)
            throws SQLException {
        if (rs.next()) {
            return buildTournament(rs);
        }
        return null;
    }

    private List<Tournament> buildMultipleInstances(final ResultSet rs)
            throws SQLException {
        List<Tournament> tournaments = new ArrayList<>();
        while (rs.next()) {
            tournaments.add(buildTournament(rs));
        }
        return tournaments;
    }

    private Tournament buildTournament(final ResultSet rs) throws SQLException {
        Tournament tournament = new Tournament();
        tournament.setId(rs.getLong("id"));
        tournament.setName(rs.getString("name"));
        TournamentTier tier = new TournamentTier();
        tier.setId(rs.getLong("tier"));
        tournament.setTier(tier);
        tournament.setPrize(rs.getInt("prize"));
        tournament.setOverview("overview");
        tournament.setStartDate(LocalDate.parse(rs.getString("start_date")));
        tournament.setEndDate(LocalDate.parse(rs.getString("end_date")));
        return tournament;
    }

    private void setUpStatement(final PreparedStatement statement,
                                final Tournament tournament) throws SQLException {
        statement.setString(1, tournament.getName());
        statement.setLong(2, tournament.getTier().getId());
        statement.setInt(3, tournament.getPrize());
        statement.setString(4, tournament.getOverview());
        statement.setDate(5, Date.valueOf(tournament.getStartDate()));
        LocalDate endDate = tournament.getEndDate();
        if (endDate != null) {
            statement.setDate(6, Date.valueOf(endDate));
        } else {
            statement.setNull(6, Types.NULL);
        }
    }
}