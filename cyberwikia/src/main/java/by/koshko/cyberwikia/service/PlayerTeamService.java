package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.PlayerTeam;
import by.koshko.cyberwikia.bean.Team;

import java.util.List;

public interface PlayerTeamService {
    List<PlayerTeam> loadPlayerTeams(Player player) throws ServiceException;
    List<PlayerTeam> loadPlayerTeams(Team team) throws ServiceException;
}
