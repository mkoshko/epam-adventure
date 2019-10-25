package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.bean.Tournament;

import java.util.List;

public interface TournamentDao extends Dao<Tournament> {
    int getRowsNumber() throws DaoException;
    List<Tournament> getAll(int offset, int limit) throws DaoException;
    List<Tournament> findByName(String name) throws DaoException;
    List<Tournament> findUpcoming(int limit) throws DaoException;
}
