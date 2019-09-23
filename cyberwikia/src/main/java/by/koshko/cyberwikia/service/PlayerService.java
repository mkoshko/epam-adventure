package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Player;

import java.util.List;

public interface PlayerService {
    Player findByNickname(String nickname);

    Player findById(long id);

    List<Player> findAll() throws ServiceException;

    Player loadProfile(long id) throws ServiceException;
}
