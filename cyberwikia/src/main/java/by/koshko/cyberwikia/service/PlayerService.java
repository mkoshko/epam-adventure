package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.ServiceResponse;

import java.util.List;

public interface PlayerService {

    ServiceResponse editPlayer(long userId, Player player)
            throws ServiceException;

    ServiceResponse createPlayer(long userId, Player player)
            throws ServiceException;

    boolean deletePlayer(long userId) throws ServiceException;

    List<Player> findPlayersByNickname(String nickname);

    Player findById(long id) throws ServiceException;

    List<Player> findAll() throws ServiceException;

    List<Player> findAll(int offset, int limit) throws ServiceException;

    Player loadProfile(long id) throws ServiceException;

    int getRowsNumber() throws ServiceException;
}
