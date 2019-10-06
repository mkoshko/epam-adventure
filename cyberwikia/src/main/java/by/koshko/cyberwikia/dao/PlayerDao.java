package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.bean.Player;

import java.util.List;

public interface PlayerDao extends Dao<Player> {
    Player findByNickname(String nickname) throws DaoException;
    List<Player> findByFullName(String fullName) throws DaoException;
    List<Player> getAll(int offset, int limit) throws DaoException;
    int getRowsNumber() throws DaoException;
}
