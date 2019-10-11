package by.koshko.cyberwikia.main;

import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.cyberpool.ConnectionPool;

import java.sql.*;

public class Test {
    public static void main(final String[] args) throws DaoException, SQLException {
        ConnectionPool.getInstance().init();
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "{CALL active_team_id(?)}";
        CallableStatement statement = connection.prepareCall(sql);
        statement.setLong(1, 7);
//        statement.registerOutParameter(1, Types.INTEGER);
        statement.execute();
        int a = statement.getInt(1);
        System.out.println("Active team: " + a);
        ConnectionPool.getInstance().close();
    }
}
