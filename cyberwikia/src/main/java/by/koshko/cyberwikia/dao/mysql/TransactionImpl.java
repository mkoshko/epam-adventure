package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.dao.*;
import by.koshko.cyberwikia.dao.pool.ConnectionPool;
import by.koshko.cyberwikia.dao.pool.ConnectionWrapper;

public class TransactionImpl implements Transaction {
    private ConnectionWrapper connection;

    public TransactionImpl() throws DaoException {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    @Override
    public CountryDao getCountryDao() {
        return new CountryDaoImpl(connection);
    }

    @Override
    public GameDao getGameDao() {
        return new GameDaoImpl(connection);
    }

    @Override
    public UserDao getUserDao() {
        return new UserDaoImpl(connection);
    }

    @Override
    public PlayerDao getPlayerDao() {
        return new PlayerDaoImpl(connection);
    }

    @Override
    public TeamDao getTeamDao() {
        return new TeamDaoImpl(connection);
    }

    @Override
    public TournamentDao getTournamentDao() {
        return new TournamentDaoImpl(connection);
    }

    @Override
    public PlayerTeamDao getPlayerTeamDao() {
        return new PlayerTeamDaoImpl(connection);
    }

    @Override
    public TournamentTeamDao getTournamentTeamDao() {
        return new TournamentTeamDaoImpl(connection);
    }

    public void close() {
        connection.close();
    }
}
