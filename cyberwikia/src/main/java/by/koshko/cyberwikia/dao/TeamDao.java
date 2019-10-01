package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.bean.Team;

public interface TeamDao extends Dao<Team> {
    Team findByName(String name) throws DaoException;
}
