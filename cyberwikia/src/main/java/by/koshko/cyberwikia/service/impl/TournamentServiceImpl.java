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

public class TournamentServiceImpl extends AbstractService
        implements TournamentService {

    private Logger logger = LogManager.getLogger(TournamentServiceImpl.class);


    public TournamentServiceImpl(final Transaction externalTransaction,
                                 final ServiceFactory factory) {
        super(externalTransaction, factory);
    }

    @Override
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
        }
    }

    @Override
    public void updateTournament(final Tournament tournament) throws ServiceException {
        TournamentValidator tournamentValidator
                = ValidationFactory.getTournamentValidator();
        if (!tournamentValidator.test(tournament, true)) {
            throw new ServiceException("Invalid tournament parameters");
        }
        try {
            TournamentDao tournamentDao = getTransaction().getDao(TOURNAMENTDAO);
            tournamentDao.update(tournament);
        } catch (DaoException e) {
            throw new ServiceException("Cannot update tournament.");
        }
    }

    @Override
    public void deleteTournament(final Tournament tournament)
            throws ServiceException {
        if (tournament == null) {
            throw new ServiceException("Cannot delete tournament.");
        }
        try {
            TournamentDao tournamentDao = getTransaction().getDao(TOURNAMENTDAO);
            tournamentDao.delete(tournament);
        } catch (DaoException e) {
            throw new ServiceException("cannot delete tournament.");
        }
    }

    @Override
    public Tournament getTournamentById(final long id) throws ServiceException {
        try {
            Transaction transaction = getTransaction();
            logger.debug("Tournament ID to find: {}", id);
            TournamentDao tournamentDao
                    = transaction.getDao(TOURNAMENTDAO);
            return tournamentDao.get(id);
        } catch (DaoException e) {
            throw new ServiceException("Cannot get tournament by ID");
        }
    }
}
