package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.PlayerTeam;
import by.koshko.cyberwikia.bean.TournamentTeam;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoTypes;
import by.koshko.cyberwikia.dao.PlayerDao;
import by.koshko.cyberwikia.dao.TournamentTeamDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.CountryService;
import by.koshko.cyberwikia.service.PlayerService;
import by.koshko.cyberwikia.service.PlayerTeamService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TeamService;
import by.koshko.cyberwikia.service.TournamentService;
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
            PlayerDao playerDao = getTransaction().getDao(PLAYERDAO);
            return playerDao.get(id);
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
            TournamentTeamDao tournamentTeamDao
                    = transaction.getDao(DaoTypes.TOURNAMENTTEAMDAO);
            CountryService countryService
                    = ServiceFactory.getCountryService(transaction);
            TeamService teamService
                    = ServiceFactory.getTeamService(transaction);
            PlayerTeamService playerTeamService
                    = ServiceFactory.getPlayerTeamService(transaction);
            TournamentService tournamentService
                    = ServiceFactory.getTournamentService(transaction);
            long countryID = player.getCountry().getId();
            player.setCountry(countryService.getCountryById(countryID));
            player.setPlayerTeams(playerTeamService.loadTeamPlayers(player, true));
            //TODO доделать после сервисов. Загрузить турниры.
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
            return playerDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        } finally {
            getTransaction().close();
        }
    }

    @Override
    public List<Player> findAll(final int offset, final int limit) throws ServiceException {
        try {
            PlayerDao playerDao = getTransaction().getDao(PLAYERDAO);
            return playerDao.getAll(offset, limit);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
