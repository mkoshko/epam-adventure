package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.dao.mysql.*;

public final class DaoFactory {

    private DaoFactory() {
    }

    public static UserDao getUserDao() {
        return new UserDaoImpl();
    }

    public static CountryDao getCountryDao() {
        return new CountryDaoImpl();
    }

    public static GameDao getGameDao() {
        return new GameDaoImpl();
    }

    public static PlayerDao getPlayerDao() {
        return new PlayerDaoImpl();
    }

    public static TeamDao getTeamDao() {
        return new TeamDaoImpl();
    }

    public static TournamentDao getTournamentDao() {
        return new TournamentDaoImpl();
    }

    public static PlayerTeamDao getPlayerTeamDao() {
        return new PlayerTeamDaoImpl();
    }
}
