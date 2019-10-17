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

    private static ImageService imageService = new ImageServiceImpl();

    public ServiceFactory() throws ServiceException {
        try {
            transaction = new TransactionImpl();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public CountryService getCountryService() {
        return new CountryServiceImpl(transaction, this);
    }

    public GameService getGameService() {
        return new GameServiceImpl(transaction, this);
    }

    public UserService getUserService() {
        return new UserServiceImpl(transaction, this);
    }

    public PlayerService getPlayerService() {
        return new PlayerServiceImpl(transaction, this);
    }

    public TeamService getTeamService() {
        return new TeamServiceImpl(transaction, this);
    }

    public PlayerTeamService getPlayerTeamService() {
        return new PlayerTeamServiceImpl(transaction, this);
    }

    public TournamentService getTournamentService() {
        return new TournamentServiceImpl(transaction, this);
    }

    public TournamentTeamService getTournamentTeamService() {
        return new TournamentTeamServiceImpl(transaction, this);
    }

    public static ImageService getImageService() {
        return imageService;
    }

    @Override
    public void close() {
        transaction.close();
    }
}
