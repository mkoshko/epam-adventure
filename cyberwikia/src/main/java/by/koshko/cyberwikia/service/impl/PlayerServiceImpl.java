package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.EntityError;
import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.PlayerTeam;
import by.koshko.cyberwikia.bean.ServiceResponse;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.PlayerDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.CountryService;
import by.koshko.cyberwikia.service.PaginationHelper;
import by.koshko.cyberwikia.service.PlayerService;
import by.koshko.cyberwikia.service.PlayerTeamService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TeamService;
import by.koshko.cyberwikia.service.TournamentTeamService;
import by.koshko.cyberwikia.service.validation.PlayerValidator;
import by.koshko.cyberwikia.service.validation.ValidationFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public final class PlayerServiceImpl extends AbstractService
        implements PlayerService {

    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger(PlayerServiceImpl.class);

    /**
     * Constructor which used by {@code ServiceFactory} if we want to obtain
     * some instance of any service with the same {@code Connection}.
     *
     * @param transaction object for obtaining {@code Dao} objects with the
     *                    same {@code Connection}.
     * @param factory     Factory for obtaining services with the same
     *                    {@code Connection}.
     */
    public PlayerServiceImpl(final Transaction transaction,
                             final ServiceFactory factory) {
        super(transaction, factory);
    }

    @Override
    public List<Player> findPlayersByNickname(final String nickname) {
        if (nickname == null || nickname.isBlank()) {
            return new ArrayList<>();
        }
        try {
            PlayerDao playerDao = getTransaction().getPlayerDao();
            return playerDao.findByNickname(nickname);
        } catch (DaoException e) {
            logger.error("Cannot find players by nickname.");
            return new ArrayList<>();
        }
    }

    @Override
    public ServiceResponse editPlayer(final long userId, final Player player)
            throws ServiceException {
        ServiceResponse response = new ServiceResponse();
        if (player == null) {
            logger.debug("Player object is null.");
            response.addErrorMessage(EntityError.GENERIC_ERROR);
            return response;
        }
        try {
            PlayerDao playerDao = getTransaction().getPlayerDao();
            Player oldPlayer = playerDao.get(userId);
            if (oldPlayer == null) {
                logger.debug("User:{} don't have permissions to edit"
                             + " Player:{} profile.", userId, player.getId());
                response.addErrorMessage(EntityError.GENERIC_ERROR);
                return response;
            }
            player.setId(oldPlayer.getId());
            if (!ValidationFactory.getPlayerValidator().test(player, true)) {
                logger.debug("Cannot edit player profile:{}."
                             + " Invalid parameters.", player.getId());
                response.addErrorMessage(EntityError.REQUIRED_NOT_NULL);
                return response;
            }
            player.setProfilePhoto(saveNewDeleteOldImage(oldPlayer
                    .getProfilePhoto(), player.getRawData()));
            if (!playerDao.update(player)) {
                response.addErrorMessage(EntityError.GENERIC_ERROR);
                return response;
            } else {
                return response;
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot update player profile.", e);
        }
    }

    @Override
    public ServiceResponse createPlayer(final long userId,
                                        final Player player)
            throws ServiceException {
        try {
            PlayerDao playerDao = getTransaction().getPlayerDao();
            Player newPlayer = playerDao.get(userId);
            if (newPlayer == null) {
                player.setId(userId);
                return createPlayer(player);
            } else {
                logger.debug("User:{} already has a player profile.", userId);
                ServiceResponse serviceResponse = new ServiceResponse();
                serviceResponse.addErrorMessage(EntityError.USER_HAS_PROFILE);
                return serviceResponse;
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot create player profile.", e);
        }
    }

    /**
     * Validates given {@code Player} object and saves it to database.
     *
     * @param player {@code Player} object to be saved.
     * @return {@code ServiceResponse} object which contains errors if any was
     * occurred.
     */
    private ServiceResponse createPlayer(final Player player) {
        ServiceResponse serviceResponse = new ServiceResponse();
        try {
            PlayerValidator playerValidator
                    = ValidationFactory.getPlayerValidator();
            if (!playerValidator.test(player, true)) {
                logger.debug("Invalid player parameters.");
                serviceResponse.addErrorMessage(EntityError.REQUIRED_NOT_NULL);
                return serviceResponse;
            }
            PlayerDao playerDao = getTransaction().getPlayerDao();
            player.setProfilePhoto(ServiceFactory.getImageService()
                    .save(player.getRawData()));
            if (playerDao.save(player)) {
                return serviceResponse;
            } else {
                serviceResponse.addErrorMessage(EntityError.GENERIC_ERROR);
                return serviceResponse;
            }
        } catch (DaoException e) {
            serviceResponse.addErrorMessage(EntityError.GENERIC_ERROR);
            logger.error("{} {}", e.getMessage(), e.getCause().getMessage());
            return serviceResponse;
        }
    }

    @Override
    public boolean deletePlayer(final long userId) throws ServiceException {
        try {
            PlayerDao playerDao = getTransaction().getPlayerDao();
            Player oldPlayer = playerDao.get(userId);
            if (oldPlayer == null) {
                return false;
            }
            ServiceFactory.getImageService()
                    .delete(oldPlayer.getProfilePhoto());
            return playerDao.delete(oldPlayer);
        } catch (DaoException e) {
            throw new ServiceException("Cannot delete player.");
        }
    }

    @Override
    public int getRowsNumber() throws ServiceException {
        try {
            PlayerDao playerDao = getTransaction().getPlayerDao();
            return playerDao.getRowsNumber();
        } catch (DaoException e) {
            throw new ServiceException("Cannot get number of records.");
        }
    }

    @Override
    public Player findById(final long id) throws ServiceException {
        try {
            Transaction transaction = getTransaction();
            PlayerDao playerDao = transaction.getPlayerDao();
            CountryService countryService
                    = getFactory().getCountryService();
            Player player = playerDao.get(id);
            if (player == null) {
                return null;
            }
            long countryID = player.getCountry().getId();
            player.setCountry(countryService.getCountryById(countryID));
            return player;
        } catch (DaoException e) {
            throw new ServiceException("Cannot get player by ID.");
        }
    }

    @Override
    public Player loadProfile(final long id) throws ServiceException {
        Transaction transaction = getTransaction();
        try {
            PlayerDao playerDao = transaction.getPlayerDao();
            Player player = playerDao.get(id);
            if (player == null) {
                return null;
            }
            CountryService countryService
                    = getFactory().getCountryService();
            PlayerTeamService playerTeamService
                    = getFactory().getPlayerTeamService();
            TournamentTeamService tournamentTeamService
                    = getFactory().getTournamentTeamService();
            long countryID = player.getCountry().getId();
            player.setCountry(countryService.getCountryById(countryID));
            player.setPlayerTeams(playerTeamService
                    .loadTeamPlayers(player, true));
            player.setTournaments(tournamentTeamService
                    .findTournamentsForPlayer(player));
            return player;
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException("Cannot load player profile.");
        }
    }

    @Override
    public List<Player> findAll(final int page, final int limit)
            throws ServiceException {
        try {
            int offset = PaginationHelper.calculateOffset(page, limit);
            PlayerDao playerDao = getTransaction().getPlayerDao();
            List<Player> players = playerDao.getAll(offset, limit);
            loadCountries(players);
            loadActiveTeams(players);
            return players;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Loads teams in which players are currently active.
     *
     * @param players list of players.
     * @throws ServiceException if some errors occurred at underlying layer.
     */
    private void loadActiveTeams(final List<Player> players)
            throws ServiceException {
        PlayerTeamService playerTeamService
                = getFactory().getPlayerTeamService();
        TeamService teamService
                = getFactory().getTeamService();
        for (Player player : players) {
            PlayerTeam playerTeam = playerTeamService.findActiveTeam(player);
            if (playerTeam != null) {
                List<PlayerTeam> teams = new ArrayList<>();
                long teamID = playerTeam.getTeam().getId();
                playerTeam.setTeam(teamService.findTeamById(teamID));
                teams.add(playerTeam);
                player.setPlayerTeams(teams);
            }
        }
    }

    /**
     * Loads {@code Country} object for players from given list.
     *
     * @param players list of players.
     * @throws ServiceException if some errors occurred at underlying layer.
     */
    private void loadCountries(final List<Player> players)
            throws ServiceException {
        CountryService countryService
                = getFactory().getCountryService();
        for (Player player : players) {
            long countryID = player.getCountry().getId();
            player.setCountry(countryService.getCountryById(countryID));
        }
    }
}
