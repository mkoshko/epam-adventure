package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Tournament;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.TournamentDao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class TournamentDaoImpl extends AbstractDao implements TournamentDao {

    private static final String GET
            = "SELECT id, name, logo_file, prize, overview, start_date, end_date"
              + " FROM tournament WHERE id=?;";
    private static final String GET_ALL
            = "SELECT id, name, logo_file, prize, overview, start_date, end_date"
              + " FROM tournament;";
    private static final String SAVE
            = "INSERT INTO tournament"
              + " (name, logo_file, prize, overview, start_date, end_date)"
              + " VALUES (?,?,?,?,?,?)";
    private static final String UPDATE
            = "UPDATE tournament SET name=?, logo_file=?, prize=?, overview=?,"
              + "start_date=?, end_date=? WHERE id=?;";
    private static final String DELETE
            = "DELETE FROM tournament WHERE id=?;";

    @Override
    public Tournament get(final long id) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(GET)) {
            statement.setLong(1, id);
            return buildSingleInstance(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException("Cannot get tournament by id.", e);
        }
    }

    @Override
    public List<Tournament> getAll() throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(GET_ALL)) {
            return buildMultipleInstances(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException("Cannot get all tournaments.", e);
        }
    }

    @Override
    public boolean save(final Tournament entity) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(SAVE)) {
            setUpStatement(statement, entity);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            return !isDuplicateError(e, "Cannot save team.");
        }
    }

    @Override
    public boolean update(final Tournament entity) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(UPDATE)) {
            setUpStatement(statement, entity);
            statement.setLong(7, entity.getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            return !isDuplicateError(e, "Cannot save team.");
        }
    }

    @Override
    public void delete(final Tournament entity) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(DELETE)) {
            statement.setLong(1, entity.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Cannot delete team.", e);
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
        tournament.setLogoFile(rs.getString("logo_file"));
        tournament.setPrize(rs.getInt("prize"));
        tournament.setOverview("overview");
        tournament.setStartDate(LocalDate.parse(rs.getString("start_date")));
        tournament.setEndDate(LocalDate.parse(rs.getString("end_date")));
        return tournament;
    }

    private void setUpStatement(final PreparedStatement statement,
                                final Tournament tournament) throws SQLException {
        int index = 1;
        statement.setString(index++, tournament.getName());
        statement.setString(index++, tournament.getLogoFile());
        statement.setInt(index++, tournament.getPrize());
        statement.setString(index++, tournament.getOverview());
        statement.setDate(index++, Date.valueOf(tournament.getStartDate()));
        statement.setDate(index, Date.valueOf(tournament.getEndDate()));
    }
}
