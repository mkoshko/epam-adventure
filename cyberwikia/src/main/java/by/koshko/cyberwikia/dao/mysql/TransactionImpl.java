package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Entity;
import by.koshko.cyberwikia.dao.CountryDao;
import by.koshko.cyberwikia.dao.Dao;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoFactory;
import by.koshko.cyberwikia.dao.DaoTypes;
import by.koshko.cyberwikia.dao.GameDao;
import by.koshko.cyberwikia.dao.PlayerDao;
import by.koshko.cyberwikia.dao.PlayerTeamDao;
import by.koshko.cyberwikia.dao.TeamDao;
import by.koshko.cyberwikia.dao.TournamentDao;
import by.koshko.cyberwikia.dao.TournamentTeamDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.dao.UserDao;
import by.koshko.cyberwikia.dao.pool.ConnectionPool;
import by.koshko.cyberwikia.dao.pool.ConnectionWrapper;

public class TransactionImpl implements Transaction {
    private ConnectionWrapper connection;

    public TransactionImpl() throws DaoException {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    public <T extends Dao<? extends Entity>> T getDao(final DaoTypes type)
            throws DaoException {
        switch (type) {
            case USERDAO:
                UserDao userDao = DaoFactory.getUserDao();
                userDao.setConnection(connection);
                return (T) userDao;
            case PLAYERDAO:
                PlayerDao playerDao = DaoFactory.getPlayerDao();
                playerDao.setConnection(connection);
                return (T) playerDao;
            case TEAMDAO:
                TeamDao teamDao = DaoFactory.getTeamDao();
                teamDao.setConnection(connection);
                return (T) teamDao;
            case GAMEDAO:
                GameDao gameDao = DaoFactory.getGameDao();
                gameDao.setConnection(connection);
                return (T) gameDao;
            case COUNTRYDAO:
                CountryDao countryDao = DaoFactory.getCountryDao();
                countryDao.setConnection(connection);
                return (T) countryDao;
            case PLAYERTEAMDAO:
                PlayerTeamDao playerTeamDao = DaoFactory.getPlayerTeamDao();
                playerTeamDao.setConnection(connection);
                return (T) playerTeamDao;
            case TOURNAMENTDAO:
                TournamentDao tournamentDao = DaoFactory.getTournamentDao();
                tournamentDao.setConnection(connection);
                return (T) tournamentDao;
            case TOURNAMENTTEAMDAO:
                TournamentTeamDao tournamentTeamDao = DaoFactory.getTournamentTeamDao();
                tournamentTeamDao.setConnection(connection);
                return (T) tournamentTeamDao;
            default:
                //TODO log
                return null;
        }
    }

    public void close() {
        connection.close();
    }
}
