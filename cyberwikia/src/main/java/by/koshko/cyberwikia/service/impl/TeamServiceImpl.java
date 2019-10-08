package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.PlayerTeam;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.bean.TournamentTeam;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.TeamDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.CountryService;
import by.koshko.cyberwikia.service.GameService;
import by.koshko.cyberwikia.service.PlayerService;
import by.koshko.cyberwikia.service.PlayerTeamService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TeamService;
import by.koshko.cyberwikia.service.TournamentTeamService;
import by.koshko.cyberwikia.service.validation.TeamValidator;
import by.koshko.cyberwikia.service.validation.ValidationFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.koshko.cyberwikia.dao.DaoTypes.TEAMDAO;

public class TeamServiceImpl extends AbstractService implements TeamService {

    private Logger logger = LogManager.getLogger(TeamServiceImpl.class);

    public TeamServiceImpl() throws ServiceException {
        super();
    }

    public TeamServiceImpl(final Transaction transaction) {
        super(transaction);
    }

    public int getRowsNumber() throws ServiceException {
        try {
            TeamDao teamDao = getTransaction().getDao(TEAMDAO);
            return teamDao.getRows();
        } catch (DaoException e) {
            throw new ServiceException("Cannot get number of rows.");
        } finally {
            close();
        }
    }

    public void updateTeam(final Team team) throws ServiceException {
        try {
            TeamValidator teamValidator = ValidationFactory.getTeamValidator();
            if (!teamValidator.test(team, true)) {
                throw new ServiceException("Invalid team parameters.");
            }
            Transaction transaction = getTransaction();
            TeamDao teamDao = transaction.getDao(TEAMDAO);
            teamDao.update(team);
        } catch (DaoException e) {
            throw new ServiceException("Cannot update team.");
        } finally {
            close();
        }
    }

    public void createTeam(final Team team) throws ServiceException {
        TeamValidator teamValidator = ValidationFactory.getTeamValidator();
        if (!teamValidator.test(team, false)) {
            throw new ServiceException("Invalid team parameters.");
        }
        Transaction transaction = getTransaction();
        try {
            TeamDao teamDao = transaction.getDao(TEAMDAO);
            team.setLogoFile(ServiceFactory
                    .getImageService().save(team.getRawData()));
            teamDao.save(team);
        } catch (DaoException e) {
            throw new ServiceException("Cannot save team.");
        } finally {
            close();
        }
    }

    public Team loadTeamProfile(final long id) throws ServiceException {
        Transaction transaction = getTransaction();
        if (id <= 0) {
            return null;
        }
        try {
            TeamDao teamDao = transaction.getDao(TEAMDAO);
            Team team = teamDao.get(id);
            if (team == null) {
                return null;
            }
            CountryService countryService
                    = ServiceFactory.getCountryService(transaction);
            GameService gameService
                    = ServiceFactory.getGameService(transaction);
            PlayerService playerService
                    = ServiceFactory.getPlayerService(transaction);
            TournamentTeamService tournamentTeamService
                    = ServiceFactory.getTournamentTeamService(transaction);
            PlayerTeamService playerTeamService
                    = ServiceFactory.getPlayerTeamService(transaction);
            long countryID = team.getCountry().getId();
            team.setCountry(countryService.getCountryById(countryID));
            long gameID = team.getGame().getId();
            team.setGame(gameService.findById(gameID));
            Player creator = team.getCreator();
            Player captain = team.getCaptain();
            Player coach = team.getCoach();
            if (creator != null) {
                creator = playerService.findById(creator.getId());
                team.setCreator(creator);
            }
            if (captain != null) {
                captain = playerService.findById(captain.getId());
                team.setCaptain(captain);
            }
            if (coach != null) {
                coach = playerService.findById(coach.getId());
                team.setCoach(coach);
            }
            List<TournamentTeam> tournaments = tournamentTeamService
                    .findTournamentsForTeam(team);
            team.setTournaments(tournaments);
            List<PlayerTeam> players
                    = playerTeamService.loadTeamPlayers(team, true);
            team.setPlayers(players);
            return team;
        } catch (DaoException e) {
            throw new ServiceException("Cannot load team profile.");
        } finally {
            close();
        }
    }

    public Team findTeamById(final long id) throws ServiceException {
        Transaction transaction = getTransaction();
        try {
            TeamDao teamDao = transaction.getDao(TEAMDAO);
            Team team = teamDao.get(id);
            CountryService countryService
                    = ServiceFactory.getCountryService(transaction);
            long countryID = team.getCountry().getId();
            Country country = countryService.getCountryById(countryID);
            team.setCountry(country);
            return team;
        } catch (DaoException e) {
            throw new ServiceException("Cannot load team by ID");
        } finally {
            close();
        }
    }

    public List<Team> findAll() throws ServiceException {
        Transaction transaction = getTransaction();
        try {
            TeamDao teamDao = transaction.getDao(TEAMDAO);
            List<Team> teams = teamDao.getAll();
            CountryService countryService
                    = ServiceFactory.getCountryService(transaction);
            PlayerService playerService = ServiceFactory.getPlayerService(transaction);
            for (Team team : teams) {
                team.setCountry(
                        countryService.getCountryById(team.getCountry().getId())
                );
                team.setCaptain(playerService.findById(team.getCaptain().getId()));
            }
            return teams;
        } catch (DaoException e) {
            throw new ServiceException("Cannot get all teams.");
        } finally {
            close();
        }
    }

    public List<Team> findAll(final int page, final int limit)
            throws ServiceException {
        Transaction transaction = getTransaction();
        try {
            int offset;
            if (page == 1) {
                offset = 0;
            } else {
                offset = (page - 1) * limit;
            }
            TeamDao teamDao = transaction.getDao(TEAMDAO);
            List<Team> teams = teamDao.getAll(offset, limit);
            CountryService countryService
                    = ServiceFactory.getCountryService(transaction);
            PlayerService playerService = ServiceFactory.getPlayerService(transaction);
            for (Team team : teams) {
                team.setCountry(
                        countryService.getCountryById(team.getCountry().getId())
                );
                team.setCaptain(playerService.findById(team.getCaptain().getId()));
            }
            return teams;
        } catch (DaoException e) {
            throw new ServiceException("Cannot get all teams.");
        } finally {
            close();
        }
    }

}
