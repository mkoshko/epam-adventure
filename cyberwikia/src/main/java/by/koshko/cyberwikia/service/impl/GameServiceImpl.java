package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Game;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoTypes;
import by.koshko.cyberwikia.dao.GameDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.GameService;
import by.koshko.cyberwikia.service.ServiceException;

public class GameServiceImpl extends AbstractService implements GameService {

    public GameServiceImpl() throws ServiceException {
        super();
    }

    public GameServiceImpl(final Transaction transaction) {
        super(transaction);
    }

    public Game findById(final long id) throws ServiceException {
        try {
            GameDao gameDao = getTransaction().getDao(DaoTypes.GAMEDAO);
            return gameDao.get(id);
        } catch (DaoException e) {
            getLogger().error("Cannot find game by id. {}", e.getMessage());
            throw new ServiceException("Cannot find game by ID.");
        } finally {
            close();
        }
    }
}
