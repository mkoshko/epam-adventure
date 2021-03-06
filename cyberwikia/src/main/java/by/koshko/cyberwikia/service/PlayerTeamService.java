package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.PlayerTeam;
import by.koshko.cyberwikia.bean.ServiceResponse;
import by.koshko.cyberwikia.bean.Team;

import java.util.List;

public interface PlayerTeamService {
    List<PlayerTeam> loadTeamPlayers(Player player, boolean deepLoad)
            throws ServiceException;
    List<PlayerTeam> loadTeamPlayers(Team team, boolean deepLoad)
            throws ServiceException;
    PlayerTeam findActiveTeam(Player player) throws ServiceException;
    int joinTeam(long userId, long teamId) throws ServiceException;
    int leaveTeam(long userId) throws ServiceException;
    long playerActiveTeamId(long playerId) throws ServiceException;
    ServiceResponse kickPlayer(long userId, long playerId)
            throws ServiceException;
}
