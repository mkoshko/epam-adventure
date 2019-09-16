package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.bean.Player;

import java.util.Optional;

public interface PlayerDao extends Dao<Player> {
    Optional<Player> findByNickname(String nickname);
    Optional<Player> findByFullName(String fullName);
}
