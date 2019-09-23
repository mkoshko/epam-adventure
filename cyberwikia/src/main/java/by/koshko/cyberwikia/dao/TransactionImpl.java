package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.bean.Entity;
import by.koshko.cyberwikia.dao.cyberpool.ConnectionPool;
import by.koshko.cyberwikia.dao.cyberpool.ConnectionWrapper;
import by.koshko.cyberwikia.dao.mysql.*;

public class TransactionImpl implements Transaction {
    private ConnectionWrapper connection;

    public TransactionImpl() throws DaoException {
        this.connection = ConnectionPool
                .access()
                .getConnection();
    }

    public <T extends Dao<? extends Entity>> T getDao(final DaoTypes type)
            throws DaoException {
        switch (type) {
            case USERDAO:
                UserDao userDao = new UserDaoImpl();
                userDao.setConnection(connection);
                return (T) userDao;
            case PLAYERDAO:
                PlayerDao playerDao = new PlayerDaoImpl();
                playerDao.setConnection(connection);
                return (T) playerDao;
            case TEAMDAO:
                TeamDao teamDao = new TeamDaoImpl();
                teamDao.setConnection(connection);
                return (T) teamDao;
            case GAMEDAO:
                GameDao gameDao = new GameDaoImpl();
                gameDao.setConnection(connection);
                return (T) gameDao;
            case COUNTRYDAO:
                CountryDao countryDao = new CountryDaoImpl();
                countryDao.setConnection(connection);
                return (T) countryDao;
            case PLAYERTEAMDAO:
                PlayerTeamDao playerTeamDao = new PlayerTeamDaoImpl();
                playerTeamDao.setConnection(connection);
                return (T) playerTeamDao;
            case TOURNAMENTDAO:
                TournamentDao tournamentDao = new TournamentDaoImpl();
                tournamentDao.setConnection(connection);
                return (T) tournamentDao;
            default:
                //TODO log
                return null;
        }
    }

    public void close() {
        connection.close();
    }
}
