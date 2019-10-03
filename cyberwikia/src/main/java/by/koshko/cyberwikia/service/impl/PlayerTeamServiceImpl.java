package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.PlayerTeam;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.PlayerTeamDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.PlayerService;
import by.koshko.cyberwikia.service.PlayerTeamService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TeamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static by.koshko.cyberwikia.dao.DaoTypes.PLAYERTEAMDAO;

public final class PlayerTeamServiceImpl extends AbstractService
        implements PlayerTeamService {

    private Logger logger = LogManager.getLogger(PlayerTeamServiceImpl.class);

    public PlayerTeamServiceImpl() throws ServiceException {
        super();
    }

    public PlayerTeamServiceImpl(final Transaction transaction) {
        super(transaction);
    }

    @Override
    public List<PlayerTeam> loadTeamPlayers(final Player player,
                                            final boolean deepLoad)
            throws ServiceException {
        if (player == null) {
            logger.warn("Method argument 'player' is null."
                        + " Empty 'PlayerTeam' list will returned.");
            return new ArrayList<>();
        }
        try {
            logger.debug("Constructing 'PlayerTeam' list for player '{}'.",
                    player.getNickname());
            PlayerTeamDao playerTeamDao
                    = getTransaction().getDao(PLAYERTEAMDAO);
            List<PlayerTeam> playerTeams = playerTeamDao.findPlayerTeam(player);
            if (deepLoad) {
                fillTeamProfiles(playerTeams);
            }
            return playerTeams;
        } catch (DaoException e) {
            logger.error("");
            throw new ServiceException("Cannot get 'PlayerTeam' list"
                                       + " for specified player");
        } finally {
            close();
        }
    }

    @Override
    public List<PlayerTeam> loadTeamPlayers(final Team team,
                                            final boolean deepLoad)
            throws ServiceException {
        if (team == null) {
            logger.warn("Method argument 'team' is null."
                        + " Empty 'PlayerTeam' list will returned.");
            return new ArrayList<>();
        }
        Transaction transaction = getTransaction();
        try {
            logger.debug("Constructing 'PlayerTeam' list for team '{}'.",
                    team.getName());
            PlayerTeamDao playerTeamDao = transaction.getDao(PLAYERTEAMDAO);
            List<PlayerTeam> players = playerTeamDao.findPlayerTeam(team);
            if (deepLoad) {
                fillPlayerProfiles(players);
            }
            return players;
        } catch (DaoException e) {
            throw new ServiceException("Cannot get 'PlayerTeam' list"
                                       + " for specified team");
        } finally {
            close();
        }
    }

    private void fillPlayerProfiles(final List<PlayerTeam> players)
            throws ServiceException {
        PlayerService playerService
                = ServiceFactory.getPlayerService(getTransaction());
        for (PlayerTeam playerTeam : players) {
            long playerID = playerTeam.getPlayer().getId();
            playerTeam.setPlayer(playerService.findById(playerID));
        }
    }

    private void fillTeamProfiles(final List<PlayerTeam> teams)
            throws ServiceException {
        TeamService teamService
                = ServiceFactory.getTeamService(getTransaction());
        for (PlayerTeam playerTeam : teams) {
            long teamID = playerTeam.getTeam().getId();
            playerTeam.setTeam(teamService.findTeamById(teamID));
        }
    }
}
