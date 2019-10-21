package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Game;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.GameDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.GameService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.koshko.cyberwikia.dao.DaoTypes.GAMEDAO;

public class GameServiceImpl extends AbstractService implements GameService {

    private Logger logger = LogManager.getLogger();

    public GameServiceImpl(final Transaction transaction,
                           final ServiceFactory factory) {
        super(transaction, factory);
    }

    public Game findById(final long id) throws ServiceException {
        try {
            GameDao gameDao = getTransaction().getDao(GAMEDAO);
            return gameDao.get(id);
        } catch (DaoException e) {
            throw new ServiceException("Cannot find game by ID.", e);
        }
    }

    public List<Game> getAll() throws ServiceException {
        try {
            GameDao gameDao = getTransaction().getDao(GAMEDAO);
            return gameDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("Cannot fetch game list.", e);
        }
    }
}
