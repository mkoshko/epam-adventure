package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.dao.CountryDao;
import by.koshko.cyberwikia.dao.DaoException;

import java.sql.Connection;
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

    public CountryDaoImpl(final Connection newConnection) {
        super(newConnection);
    }

    @Override
    public Country get(final long id) throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(GET)) {
            statement.setLong(1, id);
            return buildSingleInstance(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException("Cannot find country by ID.", e);
        }
    }

    @Override
    public List<Country> getAll() throws DaoException {
        try (PreparedStatement statement
                     = getConnection().prepareStatement(GET_ALL)) {
            return buildMultipleInstances(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException("Cannot fetch all countries.", e);
        }
    }

    @Override
    public boolean save(final Country entity) throws DaoException {
        throw new DaoException("Unsupported operation.");
    }

    @Override
    public boolean update(final Country entity) throws DaoException {
        throw new DaoException("Unsupported operation.");
    }

    @Override
    public boolean delete(final Country entity) throws DaoException {
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
