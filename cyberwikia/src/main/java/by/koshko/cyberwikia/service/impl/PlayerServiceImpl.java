package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.PlayerTeam;
import by.koshko.cyberwikia.bean.TournamentTeam;
import by.koshko.cyberwikia.dao.*;
import by.koshko.cyberwikia.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Player findById(final long id) throws ServiceException {
        try {
            PlayerDao playerDao = getTransaction().getDao(DaoTypes.PLAYERDAO);
            return playerDao.get(id);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException("Cannot load player");
        } finally {
            close();
        }
    }

    public Player loadProfile(final long id) throws ServiceException {
        try {
            PlayerDao playerDao = getTransaction().getDao(DaoTypes.PLAYERDAO);
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
            player.setPlayerTeams(pts.loadPlayerTeams(player));
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
