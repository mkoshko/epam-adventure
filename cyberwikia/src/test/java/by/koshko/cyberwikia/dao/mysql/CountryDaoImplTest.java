package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.dao.CountryDao;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.cyberpool.ConnectionPool;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.testng.Assert.*;

public class CountryDaoImplTest {

    private Connection connection;
    private CountryDao countryDao;

    @BeforeTest
    public void setUp() throws SQLException, DaoException {
        ConnectionPool.access().init();
        connection = ConnectionPool.access().getConnection();
        countryDao = new CountryDaoImpl();
        countryDao.setConnection(connection);
    }

    @AfterTest
    public void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    public void testGet() throws DaoException {
        Country country = countryDao.get(1);
        assertEquals(country.getName(), "Afghanistan");
    }

    @Test
    public void testGetAll() throws DaoException {
        List<Country> countries = countryDao.getAll();
        assertEquals(countries.size(), 249);
    }
}