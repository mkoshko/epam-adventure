package by.koshko.cyberwikia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDB {

    public static Connection getConnection() throws SQLException {
        ResourceBundle bundle = ResourceBundle.getBundle("database");
        String url = bundle.getString("db.url");
        String user = bundle.getString("db.user");
        String pass = bundle.getString("db.pass");
        return DriverManager.getConnection(url, user, pass);
    }

}
