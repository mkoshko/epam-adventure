package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.*;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static by.koshko.cyberwikia.dao.DaoTypes.PLAYERTEAMDAO;

public final class PlayerTeamServiceImpl extends AbstractService
        implements PlayerTeamService {

    private Logger logger = LogManager.getLogger(PlayerTeamServiceImpl.class);

    public PlayerTeamServiceImpl(final Transaction transaction,
                                 final ServiceFactory factory) {
        super(transaction, factory);
    }

    //TODO rethink method implementation.
    public long playerActiveTeamId(final long playerId)
            throws ServiceException {
        try {
            PlayerTeamDao playerTeamDao = getTransaction().getDao(PLAYERTEAMDAO);
            return playerTeamDao.isActiveTeamPlayer(playerId);
        } catch (DaoException e) {
            throw new ServiceException("Cannot fetch information about"
                                       + " players active team.");
        }
    }

    public ServiceResponse kickPlayer(final long userId, final long playerId)
            throws ServiceException {
        ServiceResponse response = new ServiceResponse();
        TeamService teamService = getFactory().getTeamService();
        Team createdTeam = teamService.findCreatedTeam(userId);
        if (createdTeam == null) {
            response.addErrorMessage(EntityError.GENERIC_ERROR);
            return response;
        }
        PlayerTeam playerTeam = findActiveTeam(new Player(playerId));
        if (!playerTeam.isActive()) {
            response.addErrorMessage(EntityError.PLAYER_NOT_ACTIVE);
            return response;
        }
        if (createdTeam.getId() != playerTeam.getTeam().getId()) {
            response.addErrorMessage(EntityError.GENERIC_ERROR);
            return response;
        }
        playerTeam.setActive(false);
        playerTeam.setLeaveDate(LocalDate.now());
        try {
            PlayerTeamDao playerTeamDao = getTransaction().getDao(PLAYERTEAMDAO);
            playerTeamDao.update(playerTeam);
        } catch (DaoException e) {
            logger.error("Cannot kick player. {}", e.getMessage());
            response.addErrorMessage(EntityError.GENERIC_ERROR);
            return response;
        }
        return response;
    }

    // -1 - has no player profile.
    //  0 - has no active team.
    //  1 - left.
    public int leaveTeam(final long userId)
            throws ServiceException {
        try {
            Transaction transaction = getTransaction();
            PlayerService playerService = getFactory().getPlayerService();
            Player player = playerService.findById(userId);
            if (player == null) {
                return -1;
            }
            PlayerTeam playerTeam = findActiveTeam(player);
            if (playerTeam == null) {
                return 0;
            }
            playerTeam.setActive(false);
            playerTeam.setLeaveDate(LocalDate.now());
            PlayerTeamDao playerTeamDao
                    = transaction.getDao(PLAYERTEAMDAO);
            playerTeamDao.update(playerTeam);
            TeamService teamService = getFactory().getTeamService();
            Team team = teamService.findTeamById(playerTeam.getTeam().getId());
            if (team.getCaptain() != null
                && userId == team.getCaptain().getId()) {
                team.setCaptain(null);
                teamService.updateTeam(userId, team);
                return 1;
            }
            if (team.getCoach() != null
                && userId == team.getCoach().getId()) {
                team.setCoach(null);
                teamService.updateTeam(userId, team);
                return 1;
            }
            return 1;
        } catch (DaoException e) {
            throw new ServiceException("Cannot leave team.");
        }
    }

    // -1 - has no player profile.
    //  0 - has active team.
    //  1 - joined.
    public int joinTeam(final long userId, final long teamId)
            throws ServiceException {
        try {
            Transaction transaction = getTransaction();
            PlayerService playerService = getFactory().getPlayerService();
            Player player = playerService.findById(userId);
            if (player == null) {
                return -1;
            }
            PlayerTeam playerTeam = findActiveTeam(player);
            if (playerTeam != null) {
                return 0;
            }
            PlayerTeamDao playerTeamDao = transaction.getDao(PLAYERTEAMDAO);
            playerTeam = playerTeamDao.findPlayerTeam(userId, teamId);
            if (playerTeam != null) {
                playerTeam.setActive(true);
                playerTeam.setLeaveDate(null);
                playerTeam.setJoinDate(LocalDate.now());
                playerTeamDao.update(playerTeam);
                return 1;
            }
            playerTeam = new PlayerTeam();
            playerTeam.setPlayer(player);
            Team team = new Team();
            team.setId(teamId);
            playerTeam.setTeam(team);
            //Use ZoneID.
            playerTeam.setJoinDate(LocalDate.now());
            playerTeam.setActive(true);
            playerTeamDao.save(playerTeam);
            return 1;
        } catch (DaoException e) {
            throw new ServiceException("Cannot join team.");
        }
    }

    @Override
    public List<PlayerTeam> loadTeamPlayers(final Player player,
                                            final boolean deepLoad)
            throws ServiceException {
        try {
            if (player == null) {
                logger.warn("Method argument 'player' is null."
                            + " Empty 'PlayerTeam' list will returned.");
                return new ArrayList<>();
            }
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
        }
    }

    @Override
    public List<PlayerTeam> loadTeamPlayers(final Team team,
                                            final boolean deepLoad)
            throws ServiceException {
        try {
            Transaction transaction = getTransaction();
            if (team == null) {
                logger.warn("Method argument 'team' is null."
                            + " Empty 'PlayerTeam' list will returned.");
                return new ArrayList<>();
            }
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
        }
    }

    public PlayerTeam findActiveTeam(final Player player) throws ServiceException {
        try {
            Transaction transaction = getTransaction();
            PlayerTeamDao playerTeamDao = transaction.getDao(PLAYERTEAMDAO);
            return playerTeamDao.findPlayerTeamActive(player);
        } catch (DaoException e) {
            throw new ServiceException("Cannot find players active team.");
        }
    }

    private void fillPlayerProfiles(final List<PlayerTeam> players)
            throws ServiceException {
        PlayerService playerService
                = getFactory().getPlayerService();
        for (PlayerTeam playerTeam : players) {
            long playerID = playerTeam.getPlayer().getId();
            playerTeam.setPlayer(playerService.findById(playerID));
        }
    }

    private void fillTeamProfiles(final List<PlayerTeam> teams)
            throws ServiceException {
        TeamService teamService
                = getFactory().getTeamService();
        for (PlayerTeam playerTeam : teams) {
            long teamID = playerTeam.getTeam().getId();
            playerTeam.setTeam(teamService.findTeamById(teamID));
        }
    }
}
