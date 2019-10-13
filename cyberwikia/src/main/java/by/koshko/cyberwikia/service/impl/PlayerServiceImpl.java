package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.PlayerTeam;
import by.koshko.cyberwikia.bean.ServiceResponse;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.PlayerDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.CountryService;
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

import static by.koshko.cyberwikia.dao.DaoTypes.PLAYERDAO;

public final class PlayerServiceImpl extends AbstractService
        implements PlayerService {

    private static final String NOT_SAVED_MESSAGE = "editplayer.error.notsaved";
    private static final String FILL_ALL_REQUIRED_MESSAGE
            = "editplayer.error.fillrequired";

    private Logger logger = LogManager.getLogger(PlayerServiceImpl.class);

    public PlayerServiceImpl(final Transaction transaction,
                             final ServiceFactory factory) {
        super(transaction, factory);
    }

    public ServiceResponse editPlayer(final long userId, final Player player)
            throws ServiceException {
        ServiceResponse response = new ServiceResponse();
        if (player == null) {
            logger.debug("Player object is null.");
            response.addErrorMessage(NOT_SAVED_MESSAGE);
            return response;
        }
        try {
            PlayerDao playerDao = getTransaction().getDao(PLAYERDAO);
            Player oldPlayer = playerDao.get(userId);
            if (oldPlayer == null) {
                logger.debug("User:{} don't have permissions to edit"
                             + " Player:{} profile.", userId, player.getId());
                response.addErrorMessage(NOT_SAVED_MESSAGE);
                return response;
            }
            player.setId(oldPlayer.getId());
            if (!ValidationFactory.getPlayerValidator().test(player, true)) {
                logger.debug("Cannot edit player profile:{}."
                             + " Invalid parameters.", player.getId());
                response.addErrorMessage(FILL_ALL_REQUIRED_MESSAGE);
                return response;
            }
            saveNewDeleteOldPic(player, oldPlayer);
            if (!playerDao.update(player)) {
                response.addErrorMessage(NOT_SAVED_MESSAGE);
                return response;
            } else {
                return response;
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot update player profile.", e);
        }
    }

    private void saveNewDeleteOldPic(final Player newPlayer,
                                     final Player oldPlayer) {
        if (newPlayer.getRawData() == null) {
            return;
        }
        String newPicPath = ServiceFactory
                .getImageService().save(newPlayer.getRawData());
        if (newPicPath != null) {
            logger.debug("New profile picture is set up: '{}'.", newPicPath);
            newPlayer.setProfilePhoto(newPicPath);
            ServiceFactory.getImageService().delete(oldPlayer.getProfilePhoto());
        } else {
            newPlayer.setProfilePhoto(oldPlayer.getProfilePhoto());
        }
    }

    @Override
    public boolean createPlayer(final long userId,
                                final Player player) throws ServiceException {
        try {
            PlayerDao playerDao = getTransaction().getDao(PLAYERDAO);
            Player newPlayer = playerDao.get(userId);
            if (newPlayer == null) {
                return createPlayer(player);
            } else {
                logger.debug("User:{} already has a player profile.", userId);
                return false;
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot create player profile.");
        }
    }

    private boolean createPlayer(final Player player) throws ServiceException {
        try {
            PlayerValidator playerValidator
                    = ValidationFactory.getPlayerValidator();
            if (playerValidator.test(player, true)) {
                logger.debug("Invalid player parameters.");
                return false;
            }
            PlayerDao playerDao = getTransaction().getDao(PLAYERDAO);
            player.setProfilePhoto(ServiceFactory.getImageService()
                    .save(player.getRawData()));
            playerDao.save(player);
            return true;
        } catch (DaoException e) {
            throw new ServiceException("Cannot create player profile.");
        }
    }

    @Override
    public boolean deletePlayer(final long userId, final Player player)
            throws ServiceException {
        try {
            PlayerDao playerDao = getTransaction().getDao(PLAYERDAO);
            Player oldPlayer = playerDao.get(userId);
            if (oldPlayer != null && player != null
                && oldPlayer.getId() == player.getId()) {
                deletePlayer(player);
                logger.debug("Player {}:{} has been deleted.",
                        oldPlayer.getNickname(), oldPlayer.getId());
                return true;
            } else {
                return false;
            }
        } catch (DaoException e) {
            throw new ServiceException("Cannot delete player.");
        }
    }

    private boolean deletePlayer(final Player player) throws ServiceException {
        try {
            if (player == null) {
                return false;
            }
            PlayerDao playerDao = getTransaction().getDao(PLAYERDAO);
            playerDao.delete(player);
            return true;
        } catch (DaoException e) {
            throw new ServiceException("Cannot delete player.");
        }
    }

    @Override
    public int getRowsNumber() throws ServiceException {
        try {
            PlayerDao playerDao = getTransaction().getDao(PLAYERDAO);
            return playerDao.getRowsNumber();
        } catch (DaoException e) {
            throw new ServiceException("Cannot get number of records.");
        }
    }

    @Override
    public Player findByNickname(final String nickname)
            throws ServiceException {
        try {
            if (nickname == null || nickname.isBlank()) {
                return null;
            }
            Transaction transaction = getTransaction();
            PlayerDao playerDao = transaction.getDao(PLAYERDAO);
            return playerDao.findByNickname(nickname);
        } catch (DaoException e) {
            throw new ServiceException("Cannot get player by nickname.");
        }
    }

    @Override
    public Player findById(final long id) throws ServiceException {
        try {
            Transaction transaction = getTransaction();
            PlayerDao playerDao = transaction.getDao(PLAYERDAO);
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

    public Player loadProfile(final long id) throws ServiceException {
        Transaction transaction = getTransaction();
        try {
            PlayerDao playerDao = transaction.getDao(PLAYERDAO);
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
    public List<Player> findAll() throws ServiceException {
        try {
            PlayerDao playerDao = getTransaction().getDao(PLAYERDAO);
            List<Player> players = playerDao.getAll();
            loadCountries(players);
            loadActiveTeams(players);
            return players;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Player> findAll(final int page, final int limit)
            throws ServiceException {
        try {
            int offset;
            if (page == 1) {
                offset = 0;
            } else {
                offset = (page - 1) * limit;
            }
            PlayerDao playerDao = getTransaction().getDao(PLAYERDAO);
            List<Player> players = playerDao.getAll(offset, limit);
            loadCountries(players);
            loadActiveTeams(players);
            return players;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

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
