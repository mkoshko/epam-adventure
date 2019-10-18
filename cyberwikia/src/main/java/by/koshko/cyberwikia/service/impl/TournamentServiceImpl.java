package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.*;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.TournamentDao;
import by.koshko.cyberwikia.dao.TournamentTeamDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.*;
import by.koshko.cyberwikia.service.validation.TournamentValidator;
import by.koshko.cyberwikia.service.validation.ValidationFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.koshko.cyberwikia.dao.DaoTypes.TOURNAMENTDAO;
import static by.koshko.cyberwikia.dao.DaoTypes.TOURNAMENTTEAMDAO;

public class TournamentServiceImpl extends AbstractService
        implements TournamentService {

    private Logger logger = LogManager.getLogger(TournamentServiceImpl.class);


    public TournamentServiceImpl(final Transaction externalTransaction,
                                 final ServiceFactory factory) {
        super(externalTransaction, factory);
    }

    public int getRowsNumber() throws ServiceException {
        try {
            TournamentDao tournamentDao = getTransaction().getDao(TOURNAMENTDAO);
            return tournamentDao.getRowsNumber();
        } catch (DaoException e) {
            throw new ServiceException("Cannot get rows number.", e);
        }
    }

    @Override
    public ServiceResponse createTournament(final Tournament tournament)
            throws ServiceException {
        ServiceResponse response = new ServiceResponse();
        try {
            TournamentValidator tournamentValidator
                    = ValidationFactory.getTournamentValidator();
            if (!tournamentValidator.test(tournament, false)) {
                response.addErrorMessage(EntityError.REQUIRED_NOT_NULL);
                return response;
            }
            TournamentDao tournamentDao = getTransaction().getDao(TOURNAMENTDAO);
            tournament.setLogoFile(ServiceFactory
                    .getImageService().save(tournament.getRawData()));
            tournamentDao.save(tournament);
            return response;
        } catch (DaoException e) {
            throw new ServiceException("Cannot save tournament.", e);
        }
    }

    @Override
    public ServiceResponse updateTournament(final Tournament tournament)
            throws ServiceException {
        ServiceResponse response = new ServiceResponse();
        TournamentValidator tournamentValidator
                = ValidationFactory.getTournamentValidator();
        if (!tournamentValidator.test(tournament, true)) {
            response.addErrorMessage(EntityError.REQUIRED_NOT_NULL);
            return response;
        }
        try {
            TournamentDao tournamentDao
                    = getTransaction().getDao(TOURNAMENTDAO);
            Tournament oldTournament = tournamentDao.get(tournament.getId());
            tournament.setLogoFile(saveNewDeleteOldImage(oldTournament.getLogoFile(),
                    tournament.getRawData()));
            if (tournamentDao.update(tournament)) {
                return response;
            } else {
                response.addErrorMessage(EntityError.GENERIC_ERROR);
                return response;
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot update tournament.", e);
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

    public List<Tournament> findAll(final int page, final int limit)
            throws ServiceException {
        try {
            int offset = PaginationHelper.calculateOffset(page, limit);
            TournamentDao tournamentDao = getTransaction().getDao(TOURNAMENTDAO);
            return tournamentDao.getAll(offset, limit);
        } catch (DaoException e) {
            throw new ServiceException("Cannot get all tournaments.");
        }
    }

    @Override
    public Tournament getTournamentById(final long id) throws ServiceException {
        try {
            TournamentDao tournamentDao
                    = getTransaction().getDao(TOURNAMENTDAO);
            TournamentTeamDao tournamentTeamDao
                    = getTransaction().getDao(TOURNAMENTTEAMDAO);
            Tournament tournament = tournamentDao.get(id);
            if (tournament == null) {
                return null;
            }
            List<TournamentTeam> participants
                    = tournamentTeamDao.findTournamentTeam(tournament);
            tournament.setParticipants(participants);
            TeamService teamService = getFactory().getTeamService();
            for (TournamentTeam tournamentTeam : participants) {
                tournamentTeam.setTeam(teamService
                        .findTeamById(tournamentTeam.getTeam().getId()));
            }
            return tournament;
        } catch (DaoException e) {
            throw new ServiceException("Cannot get tournament by ID", e);
        }
    }
}
