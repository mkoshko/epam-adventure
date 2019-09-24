package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.dao.CountryDao;
import by.koshko.cyberwikia.dao.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class CountryDaoImpl extends AbstractDao implements CountryDao {

    private static final String GET
            = "SELECT id, name, code, icon_file"
              + " FROM country WHERE id=?;";
    private static final String GET_ALL
            = "SELECT id, name, code, icon_file FROM country;";

    @Override
    public Country get(final long id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(GET);
            statement.setLong(1, id);
            rs = statement.executeQuery();
            return buildSingleInstance(rs);
        } catch (SQLException e) {
            logger.error("Cannot find country by ID. SQL state: {}."
                         + " Message: {}", e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot find country.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public List<Country> getAll() throws DaoException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(GET_ALL);
            rs = statement.executeQuery();
            return buildMultipleInstances(rs);
        } catch (SQLException e) {
            logger.error("Cannot fetch all countries. SQL state: {}."
                         + " Message: {}", e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot fetch all countries.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public void save(final Country entity) throws DaoException {
        throw new DaoException("Unsupported operation.");
    }

    @Override
    public void update(final Country entity) throws DaoException {
        throw new DaoException("Unsupported operation.");
    }

    @Override
    public void delete(final Country entity) throws DaoException {
        throw new DaoException("Unsupported operation.");
    }

    private Country buildSingleInstance(final ResultSet rs) throws SQLException {
        if (rs.next()) {
            return buildCountry(rs);
        }
        return null;
    }

    private List<Country> buildMultipleInstances(final ResultSet rs)
            throws SQLException {
        List<Country> countries = new ArrayList<>();
        while (rs.next()) {
            countries.add(buildCountry(rs));
        }
        return countries;
    }

    private Country buildCountry(final ResultSet rs) throws SQLException {
        Country country = new Country();
        country.setId(rs.getLong("id"));
        country.setName(rs.getString("name"));
        country.setCode(rs.getString("code"));
        country.setFlag(rs.getString("icon_file"));
        return country;
    }
}
