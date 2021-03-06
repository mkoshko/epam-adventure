package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.bean.Team;

import java.util.List;

public interface TeamDao extends Dao<Team> {
    List<Team> findByName(String name) throws DaoException;
    Team findByExactName(String name) throws DaoException;
    List<Team> getAll(int offset, int limit) throws DaoException;
    int getRows() throws DaoException;
    Team findCreatedTeam(long userId) throws DaoException;
}
