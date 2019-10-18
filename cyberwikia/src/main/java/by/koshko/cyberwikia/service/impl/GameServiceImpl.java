package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Game;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoTypes;
import by.koshko.cyberwikia.dao.GameDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.GameService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameServiceImpl extends AbstractService implements GameService {

    private Logger logger = LogManager.getLogger(GameServiceImpl.class);

    public GameServiceImpl(final Transaction transaction,
                           final ServiceFactory factory) {
        super(transaction, factory);
    }

    public Game findById(final long id) throws ServiceException {
        try {
            GameDao gameDao = getTransaction().getDao(DaoTypes.GAMEDAO);
            return gameDao.get(id);
        } catch (DaoException e) {
            throw new ServiceException("Cannot find game by ID.", e);
        }
    }
}
