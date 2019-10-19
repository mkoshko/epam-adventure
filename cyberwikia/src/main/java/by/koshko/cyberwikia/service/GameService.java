package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Game;

import java.util.List;

public interface GameService {
    Game findById(long id) throws ServiceException;
    List<Game> getAll() throws ServiceException;
}
