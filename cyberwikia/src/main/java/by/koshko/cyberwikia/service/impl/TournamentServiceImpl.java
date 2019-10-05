package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Tournament;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.TournamentDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TournamentService;
import by.koshko.cyberwikia.service.validation.TournamentValidator;
import by.koshko.cyberwikia.service.validation.ValidationFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.koshko.cyberwikia.dao.DaoTypes.TOURNAMENTDAO;

public class TournamentServiceImpl extends AbstractService implements TournamentService {

    private Logger logger = LogManager.getLogger(TournamentServiceImpl.class);

    public TournamentServiceImpl() throws ServiceException {
    }

    public TournamentServiceImpl(final Transaction externalTransaction) {
        super(externalTransaction);
    }

    public void createTournament(final Tournament tournament) throws ServiceException {
        try {
            TournamentValidator tournamentValidator
                    = ValidationFactory.getTournamentValidator();
            if (!tournamentValidator.test(tournament, false)) {
                throw new ServiceException("Invalid tournament parameters");
            }
            Transaction transaction = getTransaction();
            TournamentDao tournamentDao = transaction.getDao(TOURNAMENTDAO);
            tournament.setLogoFile(ServiceFactory
                    .getImageService().save(tournament.getRawData()));
            tournamentDao.save(tournament);
        } catch (DaoException e) {
            throw new ServiceException("Cannot save tournament.");
        } finally {
            close();
        }
    }

    public Tournament getTournamentById(final long id) throws ServiceException {
        try {
            Transaction transaction = getTransaction();
            logger.debug("Tournament ID to find: {}", id);
            TournamentDao tournamentDao
                    = transaction.getDao(TOURNAMENTDAO);
            return tournamentDao.get(id);
        } catch (DaoException e) {
            throw new ServiceException("Cannot get tournament by ID");
        } finally {
            close();
        }
    }
}
