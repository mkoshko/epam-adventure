package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.Team;

import java.util.Optional;

public interface TeamDao extends Dao<Team> {

    Team findByName(String name) throws DaoException;
}
