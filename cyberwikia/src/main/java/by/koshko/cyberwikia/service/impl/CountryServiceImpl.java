package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.dao.CountryDao;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoTypes;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.CountryService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public final class CountryServiceImpl extends AbstractService
        implements CountryService {

    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger();

    public CountryServiceImpl(final Transaction transaction,
                              final ServiceFactory factory) {
        super(transaction, factory);
    }
    @Override
    public Country getCountryById(final long id) throws ServiceException {
        try {
            CountryDao countryDao
                    = getTransaction().getDao(DaoTypes.COUNTRYDAO);
            return countryDao.get(id);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException("Cannot find country by id.", e);
        }
    }
    @Override
    public List<Country> getAll() throws ServiceException {
        try {
            CountryDao countryDao = getTransaction().getDao(DaoTypes.COUNTRYDAO);
            return countryDao.getAll();
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException("Cannot load countries.");
        }
    }
}
