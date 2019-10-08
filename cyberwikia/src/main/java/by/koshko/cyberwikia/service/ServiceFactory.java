package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.dao.mysql.TransactionImpl;
import by.koshko.cyberwikia.service.impl.CountryServiceImpl;
import by.koshko.cyberwikia.service.impl.GameServiceImpl;
import by.koshko.cyberwikia.service.impl.ImageServiceImpl;
import by.koshko.cyberwikia.service.impl.PlayerServiceImpl;
import by.koshko.cyberwikia.service.impl.PlayerTeamServiceImpl;
import by.koshko.cyberwikia.service.impl.TeamServiceImpl;
import by.koshko.cyberwikia.service.impl.TournamentServiceImpl;
import by.koshko.cyberwikia.service.impl.TournamentTeamServiceImpl;
import by.koshko.cyberwikia.service.impl.UserServiceImpl;

public class ServiceFactory implements AutoCloseable {

    private Transaction transaction;

    private ImageService imageService = new ImageServiceImpl();

    public ServiceFactory() throws ServiceException {
        try {
            transaction = new TransactionImpl();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public CountryService getCountryService() throws ServiceException {
        return new CountryServiceImpl(transaction, this);
    }

    public GameService getGameService() throws ServiceException {
        return new GameServiceImpl(transaction, this);
    }

    public UserService getUserService() throws ServiceException {
        return new UserServiceImpl(transaction, this);
    }

    public PlayerService getPlayerService() throws ServiceException {
        return new PlayerServiceImpl(transaction, this);
    }

    public TeamService getTeamService() throws ServiceException {
        return new TeamServiceImpl(transaction, this);
    }

    public PlayerTeamService getPlayerTeamService() throws ServiceException {
        return new PlayerTeamServiceImpl(transaction, this);
    }

    public TournamentService getTournamentService() throws ServiceException {
        return new TournamentServiceImpl(transaction, this);
    }

    public TournamentTeamService getTournamentTeamService() throws ServiceException {
        return new TournamentTeamServiceImpl(transaction, this);
    }

    public ImageService getImageService() {
        return imageService;
    }

    @Override
    public void close() {
        transaction.close();
    }
}
