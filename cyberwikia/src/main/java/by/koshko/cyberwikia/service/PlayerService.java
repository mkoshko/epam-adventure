package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Player;

import java.util.List;

public interface PlayerService {
    Player findByNickname(String nickname) throws ServiceException;

    Player findById(long id) throws ServiceException;

    List<Player> findAll() throws ServiceException;

    List<Player> findAll(int offset, int limit) throws ServiceException;

    Player loadProfile(long id) throws ServiceException;
}
