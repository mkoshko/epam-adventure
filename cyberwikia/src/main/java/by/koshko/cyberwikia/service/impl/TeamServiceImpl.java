package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.bean.EntityError;
import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.PlayerTeam;
import by.koshko.cyberwikia.bean.ServiceResponse;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.bean.TournamentTeam;
import by.koshko.cyberwikia.bean.User;
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
import by.koshko.cyberwikia.service.UserService;
import by.koshko.cyberwikia.service.validation.TeamValidator;
import by.koshko.cyberwikia.service.validation.ValidationFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TeamServiceImpl extends AbstractService implements TeamService {

    private Logger logger = LogManager.getLogger(TeamServiceImpl.class);

    public TeamServiceImpl(final Transaction transaction,
                           final ServiceFactory factory) {
        super(transaction, factory);
    }

    public List<Team> getTopTeams(final int limit) {
        if (limit <= 0) {
            return new ArrayList<>();
        }
        List<Long> teamsId
                =  getFactory().getTournamentTeamService().getTopTeams(limit);
        List<Team> teams = new ArrayList<>();
        try {
            for (long id : teamsId) {
                teams.add(findTeamById(id));
            }
            return teams;
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public int getRowsNumber() throws ServiceException {
        try {
            TeamDao teamDao = getTransaction().getTeamDao();
            return teamDao.getRows();
        } catch (DaoException e) {
            throw new ServiceException("Cannot get number of rows.");
        }
    }

    public List<Team> findTeamsByName(final String name) {
        if (name == null || name.isBlank()) {
            return new ArrayList<>();
        }
        try {
            TeamDao teamDao = getTransaction().getTeamDao();
            return teamDao.findByName(name);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public Team findTeamByName(final String teamName) {
        if (teamName == null || teamName.isBlank()) {
            return null;
        }
        try {
            TeamDao teamDao = getTransaction().getTeamDao();
            return teamDao.findByExactName(teamName);
        } catch (DaoException e) {
            logger.error("{} {}", e.getMessage(), e.getCause().getMessage());
            return null;
        }
    }

    @Override
    public Team findCreatedTeam(final long userId) throws ServiceException {
        try {
            TeamDao teamDao = getTransaction().getTeamDao();
            return teamDao.findCreatedTeam(userId);
        } catch (DaoException e) {
            throw new ServiceException("Cannot find player created team.", e);
        }
    }

    @Override
    public ServiceResponse updateTeam(final long userId,
                           final Team team) throws ServiceException {
        Team oldTeam = findCreatedTeam(userId);
        if (oldTeam == null) {
            ServiceResponse response = new ServiceResponse();
            response.addErrorMessage(EntityError.GENERIC_ERROR);
            return response;
        }
        team.setId(oldTeam.getId());
        team.setCreator(oldTeam.getCreator());
        return updateTeam(team, oldTeam);
    }

    @Override
    public ServiceResponse setTeamCaptain(final long userId,
                                          final long playerId) {
        ServiceResponse response = new ServiceResponse();
        try {
            TeamDao teamDao = getTransaction().getTeamDao();
            Team team = checkUserTeam(userId, playerId, response);
            if (team == null) {
                return response;
            }
            team.setCaptain(new Player(playerId));
            if (teamDao.update(team)) {
                return response;
            } else {
                response.addErrorMessage(EntityError.GENERIC_ERROR);
                return response;
            }
        } catch (ServiceException | DaoException e) {
            logger.error("Cannot set team captain. {}", e.getMessage());
            response.addErrorMessage(EntityError.GENERIC_ERROR);
            return response;
        }
    }

    public ServiceResponse setTeamCoach(final long userId,
                                        final long playerId) {
        ServiceResponse response = new ServiceResponse();
        try {
            TeamDao teamDao = getTransaction().getTeamDao();
            Team team = checkUserTeam(userId, playerId, response);
            if (team == null) {
                return response;
            }
            team.setCoach(new Player(playerId));
            if (teamDao.update(team)) {
                return response;
            } else {
                response.addErrorMessage(EntityError.GENERIC_ERROR);
                return response;
            }
        } catch (DaoException | ServiceException e) {
            logger.error("Cannot set team coach. {}", e.getMessage());
            response.addErrorMessage(EntityError.GENERIC_ERROR);
            return response;
        }
    }

    private Team checkUserTeam(final long userId, final long playerId,
                      final ServiceResponse response)
            throws ServiceException, DaoException {
        TeamDao teamDao = getTransaction().getTeamDao();
        Team team = teamDao.findCreatedTeam(userId);
        if (team == null) {
            response.addErrorMessage(EntityError.GENERIC_ERROR);
            return null;
        }
        PlayerTeamService playerTeamService
                = getFactory().getPlayerTeamService();
        long activeTeamId = playerTeamService.playerActiveTeamId(playerId);
        if (team.getId() != activeTeamId) {
            response.addErrorMessage(EntityError.PLAYER_NOT_ACTIVE);
            return null;
        }
        return team;
    }

    private ServiceResponse updateTeam(final Team team, final Team oldTeam) {
        ServiceResponse response = new ServiceResponse();
        try {
            TeamValidator teamValidator = ValidationFactory.getTeamValidator();
            if (!teamValidator.test(team, true)) {
                response.addErrorMessage(EntityError.REQUIRED_NOT_NULL);
                return response;
            }
            TeamDao teamDao = getTransaction().getTeamDao();
            team.setLogoFile(saveNewDeleteOldImage(oldTeam.getLogoFile(),
                    team.getRawData()));
            if (teamDao.update(team)) {
                return response;
            } else {
                response.addErrorMessage(EntityError.DUPLICATE_TEAMNAME);
                return response;
            }
        } catch (DaoException e) {
            logger.error("Cannot update team. {}", e.getMessage());
            response.addErrorMessage(EntityError.GENERIC_ERROR);
            return response;
        }
    }

    @Override
    public ServiceResponse createTeam(final long userId,
                           final Team team) throws ServiceException {
        Team oldTeam = findCreatedTeam(userId);
        if (oldTeam != null) {
            ServiceResponse serviceResponse = new ServiceResponse();
            serviceResponse.addErrorMessage(EntityError.ALREADY_HAS_TEAM);
            return serviceResponse;
        }
        team.setCreator(new User(userId));
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
            TeamDao teamDao = getTransaction().getTeamDao();
            team.setLogoFile(ServiceFactory
                    .getImageService().save(team.getRawData()));
            if (teamDao.save(team)) {
                return response;
            } else {
                response.addErrorMessage(EntityError.DUPLICATE_TEAMNAME);
                return response;
            }
        } catch (DaoException e) {
            logger.error("Cannot create team. {}", e.getMessage());
            response.addErrorMessage(EntityError.GENERIC_ERROR);
            return response;
        }
    }

    public ServiceResponse deleteTeam(final long userId) {
        ServiceResponse response = new ServiceResponse();
        try {
            TeamDao teamDao = getTransaction().getTeamDao();
            Team team = teamDao.findCreatedTeam(userId);
            if (team == null || !teamDao.delete(team)) {
                response.addErrorMessage(EntityError.GENERIC_ERROR);
                return response;
            }
            logger.debug("Team '{}' was deleted.", team.getName());
            return response;
        } catch (DaoException e) {
            logger.error("Cannot delete team. User id: {}. {}",
                    userId, e.getMessage());
            response.addErrorMessage(EntityError.GENERIC_ERROR);
            return response;
        }
    }

    @Override
    public Team loadTeamProfile(final long id) throws ServiceException {
        Transaction transaction = getTransaction();
        if (id <= 0) {
            return null;
        }
        try {
            TeamDao teamDao = transaction.getTeamDao();
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
            UserService userService = getFactory().getUserService();
            long countryID = team.getCountry().getId();
            team.setCountry(countryService.getCountryById(countryID));
            long gameID = team.getGame().getId();
            team.setGame(gameService.findById(gameID));
            User creator = team.getCreator();
            Player captain = team.getCaptain();
            Player coach = team.getCoach();
            if (creator != null) {
                creator = userService.get(team.getCreator().getId());
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
            throw new ServiceException("Cannot load team profile.", e);
        }
    }

    public Team findTeamById(final long id) throws ServiceException {
        Transaction transaction = getTransaction();
        try {
            TeamDao teamDao = transaction.getTeamDao();
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
            TeamDao teamDao = transaction.getTeamDao();
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
            throw new ServiceException("Cannot get all teams.", e);
        }
    }

}
