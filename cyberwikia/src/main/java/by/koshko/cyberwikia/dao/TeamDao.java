package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.bean.Team;

import java.util.Optional;

public interface TeamDao extends Dao<Team> {

    Optional<Team> findByName(String name) throws DaoException;
}
