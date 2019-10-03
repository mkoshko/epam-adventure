package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Tournament;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoTypes;
import by.koshko.cyberwikia.dao.TournamentDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.TournamentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TournamentServiceImpl extends AbstractService implements TournamentService {

    private Logger logger = LogManager.getLogger(TournamentServiceImpl.class);

    public TournamentServiceImpl() throws ServiceException {
    }

    public TournamentServiceImpl(final Transaction externalTransaction) {
        super(externalTransaction);
    }

    public Tournament getTournamentById(final long id) throws ServiceException {
        Transaction transaction = getTransaction();
        try {
            logger.debug("Tournament ID to find: {}", id);
            TournamentDao tournamentDao
                    = transaction.getDao(DaoTypes.TOURNAMENTDAO);
            return tournamentDao.get(id);
        } catch (DaoException e) {
            throw new ServiceException("Cannot get tournament by ID");
        } finally {
            close();
        }
    }
}
