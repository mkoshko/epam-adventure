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

public class TournamentTeamServiceImpl extends AbstractService
        implements TournamentTeamService {

    private Logger logger = LogManager.getLogger(TournamentTeamServiceImpl.class);


    public TournamentTeamServiceImpl(final Transaction transaction,
                                     final ServiceFactory factory) {
        super(transaction, factory);
    }

    public List<Long> getTopTeams(final int limit) {
        if (limit <= 0) {
            return new ArrayList<>();
        }
        try {
            TournamentTeamDao ttd = getTransaction().getTournamentTeamDao();
            return ttd.getTopTeams(limit);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public void updateTournamentTeam(final TournamentTeam tournamentTeam)
            throws ServiceException {
        try {
            if (!TournamentTeamValidator.test(tournamentTeam)) {
                throw new ServiceException("Cannot update the information about"
                                           + " the tournament participant.");
            }
            TournamentTeamDao tournamentTeamDao
                    = getTransaction().getTournamentTeamDao();
            tournamentTeamDao.update(tournamentTeam);
            logger.debug("Tournament participant information was updated.");
        } catch (DaoException e) {
            throw new ServiceException("Cannot update tournament information.");
        }
    }

    public void addTournamentTeam(final TournamentTeam tournamentTeam)
            throws ServiceException {
        Transaction transaction = getTransaction();
        try {
            if (!TournamentTeamValidator.test(tournamentTeam)) {
                throw new ServiceException("Cannot add team"
                                      + " to tournament participants list.");
            }
            TournamentTeamDao tournamentTeamDao
                    = transaction.getTournamentTeamDao();
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
        try {
            if (team == null) {
                logger.warn("Method argument 'team' is null."
                            + " Empty 'TournamentTeam' list will returned.");
                return new ArrayList<>();
            }
            Transaction transaction = getTransaction();
            TournamentTeamDao tournamentTeamDao
                    = transaction.getTournamentTeamDao();
            TournamentService tournamentService
                    = getFactory().getTournamentService();
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
        }
    }

    public List<TournamentTeam> findTournamentsForPlayer(final Player player)
            throws ServiceException {
        try {
            if (player == null) {
                logger.debug("Method argument 'player' is null."
                            + " Empty 'TournamentTeam' list will returned.");
                return new ArrayList<>();
            }
            Transaction transaction = getTransaction();
            TournamentTeamDao tournamentTeamDao
                    = transaction.getTournamentTeamDao();
            TeamService teamService =
                    getFactory().getTeamService();
            TournamentService tournamentService
                    = getFactory().getTournamentService();
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
            throw new ServiceException("Cannot get tournament list for player", e);
        }
    }
}
