package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.PlayerTeam;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.PlayerDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.*;
import by.koshko.cyberwikia.service.validation.PlayerValidator;
import by.koshko.cyberwikia.service.validation.ValidationFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static by.koshko.cyberwikia.dao.DaoTypes.PLAYERDAO;

public final class PlayerServiceImpl extends AbstractService
        implements PlayerService {

    private Logger logger = LogManager.getLogger(PlayerServiceImpl.class);

    public PlayerServiceImpl() throws ServiceException {
        super();
    }

    public PlayerServiceImpl(final Transaction transaction) {
        super(transaction);
    }

    public void createPlayer(final Player player) throws ServiceException {
        try {
            PlayerValidator playerValidator
                    = ValidationFactory.getPlayerValidator();
            PlayerDao playerDao = getTransaction().getDao(PLAYERDAO);
            if (playerValidator.test(player, true)) {
                throw new ServiceException("Invalid player parameters.");
            }
            player.setProfilePhoto(ServiceFactory.getImageService()
                    .save(player.getRawData()));
            playerDao.save(player);
        } catch (DaoException e) {
            throw new ServiceException("Cannot create player profile.");
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
        } finally {
            close();
        }
    }

    @Override
    public Player findById(final long id) throws ServiceException {
        try {
            Transaction transaction = getTransaction();
            PlayerDao playerDao = transaction.getDao(PLAYERDAO);
            CountryService countryService
                    = ServiceFactory.getCountryService(transaction);
            Player player = playerDao.get(id);
            long countryID = player.getCountry().getId();
            player.setCountry(countryService.getCountryById(countryID));
            return player;
        } catch (DaoException e) {
            throw new ServiceException("Cannot get player by ID.");
        } finally {
            close();
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
                    = ServiceFactory.getCountryService(transaction);
            PlayerTeamService playerTeamService
                    = ServiceFactory.getPlayerTeamService(transaction);
            TournamentTeamService tournamentTeamService
                    = ServiceFactory.getTournamentTeamService(transaction);
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
        } finally {
            close();
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
        } finally {
            getTransaction().close();
        }
    }

    @Override
    public List<Player> findAll(final int page, final int limit) throws ServiceException {
        try {
            int offset = page * limit;
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
                = ServiceFactory.getPlayerTeamService(getTransaction());
        TeamService teamService
                = ServiceFactory.getTeamService(getTransaction());
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
        Transaction transaction = getTransaction();
        CountryService countryService
                = ServiceFactory.getCountryService(transaction);
        for (Player player : players) {
            long countryID = player.getCountry().getId();
            player.setCountry(countryService.getCountryById(countryID));
        }
    }
}
