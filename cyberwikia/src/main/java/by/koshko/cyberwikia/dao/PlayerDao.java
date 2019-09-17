package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.bean.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerDao extends Dao<Player> {
    Optional<Player> findByNickname(String nickname) throws DaoException;
    List<Player> findByFullName(String fullName) throws DaoException;
}
