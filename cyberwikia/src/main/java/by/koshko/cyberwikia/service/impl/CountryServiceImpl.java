package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.dao.CountryDao;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoTypes;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.CountryService;
import by.koshko.cyberwikia.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CountryServiceImpl extends AbstractService implements CountryService {

    private Logger logger = LogManager.getLogger(CountryServiceImpl.class);

    public CountryServiceImpl() throws ServiceException {
        super();
    }

    public CountryServiceImpl(final Transaction transaction) {
        super(transaction);
    }

    public Country getCountryById(final long id) throws ServiceException {
        try {
            CountryDao countryDao = getTransaction().getDao(DaoTypes.COUNTRYDAO);
            return countryDao.get(id);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException("Cannot load country");
        } finally {
            close();
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
            logger.error(e.getMessage());
            throw new ServiceException("Cannot load countries.");
        }
    }

}
