package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.impl.*;

public class ServiceFactory {

    private ServiceFactory() {
    }

    public static CountryService getCountryService() throws ServiceException {
        return new CountryServiceImpl();
    }

    public static CountryService getCountryService(final Transaction t) {
        return new CountryServiceImpl(t);
    }

    public static GameService getGameService() throws ServiceException {
        return new GameServiceImpl();
    }

    public static GameService getGameService(final Transaction t) {
        return new GameServiceImpl(t);
    }

    public static UserService getUserService() throws ServiceException {
        return new UserServiceImpl();
    }

    public static UserService getUserService(final Transaction t) {
        return new UserServiceImpl(t);
    }

    public static PlayerService getPlayerService() throws ServiceException {
        return new PlayerServiceImpl();
    }

    public static PlayerService getPlayerService(final Transaction t) {
        return new PlayerServiceImpl(t);
    }

    public static TeamService getTeamService() throws ServiceException {
        return new TeamServiceImpl();
    }

    public static TeamService getTeamService(final Transaction t) {
        return new TeamServiceImpl(t);
    }
}
