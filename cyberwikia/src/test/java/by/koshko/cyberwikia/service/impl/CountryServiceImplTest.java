package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.service.CountryService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class CountryServiceImplTest extends AbstractServiceTest {

    private ServiceFactory factory;
    private CountryService countryService;
    private final int numberOfCountries = 249;

    @BeforeClass
    public void setUp() throws ServiceException {
        factory = new ServiceFactory();
        countryService = factory.getCountryService();
    }

    @AfterClass
    public void tearDown() {
        factory.close();
    }

    @DataProvider(name = "id_provider")
    private Object[][] provideId() {
        return new Object[][] {
                {1, "Afghanistan"},
                {2, "Aland Islands"},
                {3, "Albania"},
                {4, "Algeria"},
                {5, "American Samoa"},
                {100, "Hong Kong"},
        };
    }
    @DataProvider(name = "id_provider_incorrect")
    private Object[][] provideIdIncorrect() {
        return new Object[][] {
                {-1},
                {0},
                {1065}
        };
    }

    @Test(dataProvider = "id_provider")
    public void testGetCountryById(final int countryId, final String countryName)
            throws ServiceException {
        Country country = countryService.getCountryById(countryId);
        assertEquals(country.getName(), countryName);
    }

    @Test(dataProvider = "id_provider_incorrect")
    public void testGetCountryById2(final int countryId)
            throws ServiceException {
        Country country = countryService.getCountryById(countryId);
        assertNull(country);
    }

    @Test
    public void testGetAll() throws ServiceException {
        List<Country> countries = countryService.getAll();
        assertEquals(countries.size(), numberOfCountries);
    }
}