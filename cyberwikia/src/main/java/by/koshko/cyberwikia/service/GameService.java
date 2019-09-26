package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Game;

public interface GameService {
    Game findById(long id) throws ServiceException;
}
