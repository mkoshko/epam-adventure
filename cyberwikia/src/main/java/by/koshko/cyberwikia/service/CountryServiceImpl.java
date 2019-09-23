package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.dao.CountryDao;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoTypes;
import by.koshko.cyberwikia.dao.Transaction;

import java.util.Optional;

public class CountryServiceImpl extends AbstractService implements CountryService {

    public CountryServiceImpl() throws ServiceException {
        super();
    }

    public CountryServiceImpl(final Transaction transaction) {
        super(transaction);
    }

    public Country getCountryById(final long id) throws ServiceException {
        try {
            CountryDao countryDao = transaction.getDao(DaoTypes.COUNTRYDAO);
            Optional<Country> country = countryDao.get(id);
            return country.orElse(null);
        } catch (DaoException e) {
            throw new ServiceException("");
        }
    }

}
