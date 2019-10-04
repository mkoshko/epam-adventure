package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.bean.TournamentTeam;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.TournamentTeamDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TeamService;
import by.koshko.cyberwikia.service.TournamentService;
import by.koshko.cyberwikia.service.TournamentTeamService;
import by.koshko.cyberwikia.service.validation.TournamentTeamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static by.koshko.cyberwikia.dao.DaoTypes.TOURNAMENTTEAMDAO;

public class TournamentTeamServiceImpl extends AbstractService
        implements TournamentTeamService {

    private Logger logger = LogManager.getLogger(TournamentTeamServiceImpl.class);

    public TournamentTeamServiceImpl() throws ServiceException {
    }

    public TournamentTeamServiceImpl(final Transaction externalTransaction) {
        super(externalTransaction);
    }

    public void updateTournamentTeam(final TournamentTeam tournamentTeam)
            throws ServiceException {
        if (!TournamentTeamValidator.test(tournamentTeam)) {
            throw new ServiceException("Cannot update the information about"
                                       + " the tournament participant.");
        }
        Transaction transaction = getTransaction();
        try {
            TournamentTeamDao tournamentTeamDao
                    = transaction.getDao(TOURNAMENTTEAMDAO);
            tournamentTeamDao.update(tournamentTeam);
            logger.debug("Tournament participant information was updated.");
        } catch (DaoException e) {
            throw new ServiceException("Cannot update tournament information.");
        }
    }

    public void addTournamentTeam(final TournamentTeam tournamentTeam)
            throws ServiceException {
        if (!TournamentTeamValidator.test(tournamentTeam)) {
            throw new ServiceException("Cannot add team"
                                       + " to tournament participants list.");
        }
        Transaction transaction = getTransaction();
        try {
            TournamentTeamDao tournamentTeamDao
                    = transaction.getDao(TOURNAMENTTEAMDAO);
            tournamentTeamDao.save(tournamentTeam);
            logger.debug("Team {} was added to '{}' participants list",
                    tournamentTeam.getTeam().getName(),
                    tournamentTeam.getTournament().getName());
        } catch (DaoException e) {
            throw new ServiceException("Cannot add team"
                                       + " to tournament participants list.");
        }
    }

    public List<TournamentTeam> findTournamentsForTeam(final Team team)
            throws ServiceException {
        if (team == null) {
            logger.warn("Method argument 'team' is null."
                        + " Empty 'TournamentTeam' list will returned.");
            return new ArrayList<>();
        }
        try {
            Transaction transaction = getTransaction();
            TournamentTeamDao tournamentTeamDao
                    = transaction.getDao(TOURNAMENTTEAMDAO);
            TournamentService tournamentService
                    = ServiceFactory.getTournamentService(transaction);
            List<TournamentTeam> tournaments
                    =  tournamentTeamDao.findTournamentTeam(team);
            for (TournamentTeam entity : tournaments) {
                long tournamentID = entity.getTournament().getId();
                entity.setTournament(tournamentService
                        .getTournamentById(tournamentID));
                entity.setTeam(team);
            }
            return tournaments;
        } catch (DaoException e) {
            throw new ServiceException("Cannot get tournament list for team.");
        } finally {
            close();
        }
    }

    public List<TournamentTeam> findTournamentsForPlayer(final Player player)
            throws ServiceException {
        if (player == null) {
            logger.warn("Method argument 'player' is null."
                        + " Empty 'TournamentTeam' list will returned.");
            return new ArrayList<>();
        }
        try {
            Transaction transaction = getTransaction();
            TournamentTeamDao tournamentTeamDao
                    = transaction.getDao(TOURNAMENTTEAMDAO);
            TeamService teamService =
                    ServiceFactory.getTeamService(transaction);
            TournamentService tournamentService
                    = ServiceFactory.getTournamentService(transaction);
            List<TournamentTeam> tournaments
                    =  tournamentTeamDao.findTournamentTeam(player);
            for (TournamentTeam entity : tournaments) {
                long teamID = entity.getTeam().getId();
                long tournamentID = entity.getTournament().getId();
                entity.setTeam(teamService.findTeamById(teamID));
                entity.setTournament(tournamentService
                        .getTournamentById(tournamentID));
            }
            return tournaments;
        } catch (DaoException e) {
            throw new ServiceException("Cannot get tournament list for player");
        } finally {
            close();
        }
    }
}
