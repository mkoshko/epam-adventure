package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.Team;

import java.util.Optional;

public interface TeamDao extends Dao<Team> {

    Optional<Team> findByName(String name) throws DaoException;
    void addPlayer(Player player, Team team) throws DaoException;
    void removePlayer(Player player, Team team) throws DaoException;
    void disbandPlayer(Player player, Team team) throws DaoException;
}
