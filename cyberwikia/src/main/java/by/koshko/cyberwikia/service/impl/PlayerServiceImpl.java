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
            PlayerDao playerDao = getTransaction().getDao(PLAYERDAO);
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
        try {
            PlayerDao playerDao = getTransaction().getDao(PLAYERDAO);
            Player player = playerDao.get(id);
            if (player == null) {
                return null;
            }
            TournamentTeamDao ttd = getTransaction().getDao(DaoTypes.TOURNAMENTTEAMDAO);
            CountryService cs = ServiceFactory.getCountryService(getTransaction());
            TeamService ts = ServiceFactory.getTeamService(getTransaction());
            PlayerTeamService pts = ServiceFactory.getPlayerTeamService(getTransaction());
            TournamentService trs = ServiceFactory.getTournamentService(getTransaction());
            player.setCountry(cs.getCountryById(player.getCountry().getId()));
            player.setPlayerTeams(pts.loadTeamPlayers(player));
            for (PlayerTeam pt : player.getPlayerTeams()) {
                pt.setTeam(ts.findTeamById(pt.getTeam().getId()));
            }
            player.setTournaments(ttd.findTournamentTeam(player));
            for (TournamentTeam tt : player.getTournaments()) {
                tt.setTeam(ts.findTeamById(tt.getTeam().getId()));
                tt.setTournament(trs.getTournamentById(tt.getTournament().getId()));
            }
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
