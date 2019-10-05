package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.PlayerTeam;
import by.koshko.cyberwikia.bean.Team;

import java.util.List;

public interface PlayerTeamDao extends Dao<PlayerTeam> {

    List<PlayerTeam> findPlayerTeam(Team team) throws DaoException;
    List<PlayerTeam> findPlayerTeam(Player player) throws DaoException;
    PlayerTeam findPlayerTeamActive(Player player) throws DaoException;
}
