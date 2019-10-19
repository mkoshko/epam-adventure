package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.*;
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

    public TeamServiceImpl(final Transaction transaction,
                           final ServiceFactory factory) {
        super(transaction, factory);
    }

    public int getRowsNumber() throws ServiceException {
        try {
            TeamDao teamDao = getTransaction().getDao(TEAMDAO);
            return teamDao.getRows();
        } catch (DaoException e) {
            throw new ServiceException("Cannot get number of rows.");
        }
    }

    public Team findCreatedTeam(final long userId) throws ServiceException {
        try {
            TeamDao teamDao = getTransaction().getDao(TEAMDAO);
            return teamDao.findCreatedTeam(userId);
        } catch (DaoException e) {
            throw new ServiceException("Cannot find player created team.", e);
        }
    }

    @Override
    public void updateTeam(final long userId,
                           final Team team) throws ServiceException {
        PlayerService playerService = getFactory().getPlayerService();
        Player creator = playerService.findById(userId);
        if (creator == null) {
            throw new ServiceException("User with no player profile cannot"
                                       + " update team information.");
        }
        if (team != null && team.getCreator() != null
            && team.getCreator().getId() == creator.getId()) {
            updateTeam(team);
        } else {
            throw new ServiceException("User has no permission"
                                       + " to update the team.");
        }
    }

    private void updateTeam(final Team team) throws ServiceException {
        try {
            TeamValidator teamValidator = ValidationFactory.getTeamValidator();
            if (!teamValidator.test(team, true)) {
                throw new ServiceException("Invalid team parameters.");
            }
            Transaction transaction = getTransaction();
            TeamDao teamDao = transaction.getDao(TEAMDAO);
            teamDao.update(team);
        } catch (DaoException e) {
            throw new ServiceException("Cannot update team.", e);
        }
    }

    @Override
    public ServiceResponse createTeam(final long userId,
                           final Team team) throws ServiceException {
        PlayerService playerService = getFactory().getPlayerService();
        Player player = playerService.findById(userId);
        if (player == null) {
            ServiceResponse response = new ServiceResponse();
            response.addErrorMessage(EntityError.NO_PLAYER_PROFILE);
            return response;
        }
        team.setCreator(player);
        return createTeam(team);
    }

    private ServiceResponse createTeam(final Team team) {
        ServiceResponse response = new ServiceResponse();
        TeamValidator teamValidator = ValidationFactory.getTeamValidator();
        if (!teamValidator.test(team, false)) {
            response.addErrorMessage(EntityError.REQUIRED_NOT_NULL);
            return response;
        }
        try {
            TeamDao teamDao = getTransaction().getDao(TEAMDAO);
            team.setLogoFile(ServiceFactory
                    .getImageService().save(team.getRawData()));
            if (teamDao.save(team)) {
                return response;
            } else {
                response.addErrorMessage(EntityError.GENERIC_ERROR);
                return response;
            }
        } catch (DaoException e) {
            logger.error("Cannot create team. {}", e.getMessage());
            response.addErrorMessage(EntityError.GENERIC_ERROR);
            return response;
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
                    = getFactory().getCountryService();
            GameService gameService
                    = getFactory().getGameService();
            PlayerService playerService
                    = getFactory().getPlayerService();
            TournamentTeamService tournamentTeamService
                    = getFactory().getTournamentTeamService();
            PlayerTeamService playerTeamService
                    = getFactory().getPlayerTeamService();
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
        }
    }

    public Team findTeamById(final long id) throws ServiceException {
        Transaction transaction = getTransaction();
        try {
            TeamDao teamDao = transaction.getDao(TEAMDAO);
            Team team = teamDao.get(id);
            CountryService countryService
                    = getFactory().getCountryService();
            long countryID = team.getCountry().getId();
            Country country = countryService.getCountryById(countryID);
            team.setCountry(country);
            return team;
        } catch (DaoException e) {
            throw new ServiceException("Cannot load team by ID");
        }
    }

    public List<Team> findAll() throws ServiceException {
        Transaction transaction = getTransaction();
        try {
            TeamDao teamDao = transaction.getDao(TEAMDAO);
            List<Team> teams = teamDao.getAll();
            CountryService countryService
                    = getFactory().getCountryService();
            PlayerService playerService = getFactory().getPlayerService();
            for (Team team : teams) {
                team.setCountry(
                        countryService.getCountryById(team.getCountry().getId())
                );
                team.setCaptain(playerService.findById(team.getCaptain().getId()));
            }
            return teams;
        } catch (DaoException e) {
            throw new ServiceException("Cannot get all teams.");
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
                    = getFactory().getCountryService();
            PlayerService playerService = getFactory().getPlayerService();
            for (Team team : teams) {
                team.setCountry(
                        countryService.getCountryById(team.getCountry().getId())
                );
                if (team.getCaptain() != null) {
                    team.setCaptain(playerService
                            .findById(team.getCaptain().getId()));
                }
            }
            return teams;
        } catch (DaoException e) {
            throw new ServiceException("Cannot get all teams.");
        }
    }

}
