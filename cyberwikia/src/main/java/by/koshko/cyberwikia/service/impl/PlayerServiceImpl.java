package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.PlayerDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.CountryService;
import by.koshko.cyberwikia.service.PlayerService;
import by.koshko.cyberwikia.service.PlayerTeamService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TournamentTeamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.koshko.cyberwikia.dao.DaoTypes.PLAYERDAO;

public final class PlayerServiceImpl extends AbstractService implements PlayerService {

    private Logger logger = LogManager.getLogger(PlayerServiceImpl.class);

    public PlayerServiceImpl() throws ServiceException {
        super();
    }

    public PlayerServiceImpl(final Transaction transaction) {
        super(transaction);
    }

    @Override
    public Player findByNickname(final String nickname)
            throws ServiceException {
        if (nickname == null || nickname.isBlank()) {
            return null;
        }
        try {
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
            return players;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
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
