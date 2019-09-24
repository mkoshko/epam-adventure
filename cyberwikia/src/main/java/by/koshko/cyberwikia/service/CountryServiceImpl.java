package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.dao.CountryDao;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoTypes;
import by.koshko.cyberwikia.dao.Transaction;

import java.util.List;

public class CountryServiceImpl extends AbstractService implements CountryService {

    public CountryServiceImpl() throws ServiceException {
        super();
    }

    public CountryServiceImpl(final Transaction transaction) {
        super(transaction);
    }

    public Country getCountryById(final long id) throws ServiceException {
        try {
            CountryDao countryDao = getTransaction().getDao(DaoTypes.COUNTRYDAO);
            Country country = countryDao.get(id);
            getTransaction().close();
            return country;
        } catch (DaoException e) {
            getLogger().error(e.getMessage());
            throw new ServiceException("Cannot load country");
        }
    }
    @Override
    public List<Country> getAll() throws ServiceException {
        try {
            CountryDao countryDao = getTransaction().getDao(DaoTypes.COUNTRYDAO);
            List<Country> countries = countryDao.getAll();
            getTransaction().close();
            return countries;
        } catch (DaoException e) {
            getLogger().error(e.getMessage());
            throw new ServiceException("Cannot load countries.");
        }
    }

}
