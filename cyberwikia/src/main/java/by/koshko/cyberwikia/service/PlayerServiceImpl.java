package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.PlayerTeam;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoTypes;
import by.koshko.cyberwikia.dao.PlayerDao;

import java.util.List;

public final class PlayerServiceImpl extends AbstractService implements PlayerService {

    public PlayerServiceImpl() throws ServiceException {
        super();
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
            PlayerTeamService pts = new PlayerTeamService();
            player.setCountry(cs.getCountryById(player.getCountry().getId()));
            player.setPlayerTeams(pts.loadPlayerTeams(player));
            for (PlayerTeam pt : player.getPlayerTeams()) {
                pt.setTeam(ts.findTeamById(pt.getTeam().getId()));
            }
            return player;
        } catch (DaoException e) {
            getLogger().error(e.getMessage());
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
