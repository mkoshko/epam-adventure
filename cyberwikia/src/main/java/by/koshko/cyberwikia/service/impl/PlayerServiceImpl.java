package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.PlayerTeam;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoTypes;
import by.koshko.cyberwikia.dao.PlayerDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.PlayerService;
import by.koshko.cyberwikia.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public final class PlayerServiceImpl extends AbstractService implements PlayerService {

    private Logger logger = LogManager.getLogger(PlayerServiceImpl.class);

    public PlayerServiceImpl() throws ServiceException {
        super();
    }

    public PlayerServiceImpl(final Transaction transaction) {
        super(transaction);
    }

    @Override
    public Player findByNickname(final String nickname) {
        return null;
    }

    @Override
    public Player findById(final long id) {
        return null;
    }

    public Player loadProfile(final long id) throws ServiceException {
        try {
            PlayerDao playerDao = getTransaction().getDao(DaoTypes.PLAYERDAO);
            Player player = playerDao.get(id);
            if (player == null) {
                return null;
            }
            CountryServiceImpl cs = new CountryServiceImpl(getTransaction());
            TeamServiceImpl ts = new TeamServiceImpl(getTransaction());
            PlayerTeamServiceImpl pts = new PlayerTeamServiceImpl(getTransaction());
            player.setCountry(cs.getCountryById(player.getCountry().getId()));
            player.setPlayerTeams(pts.loadPlayerTeams(player));
            for (PlayerTeam pt : player.getPlayerTeams()) {
                pt.setTeam(ts.findTeamById(pt.getTeam().getId()));
            }
            close();
            return player;
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException("Cannot load player profile.");
        }
    }

    @Override
    public List<Player> findAll() throws ServiceException {
        try {
            PlayerDao playerDao = getTransaction().getDao(DaoTypes.PLAYERDAO);
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
            PlayerDao playerDao = getTransaction().getDao(DaoTypes.PLAYERDAO);
            return playerDao.getAll(offset, limit);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
