package by.koshko.cyberwikia.main;

import by.koshko.cyberwikia.ConnectorDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Main {
    public static void main(final String[] args) throws SQLException {
        ResourceBundle bundle = ResourceBundle.getBundle("database");
        String mysqlDriver = bundle.getString("db.driver");
        Connection connection = ConnectorDB.getConnection();
        Statement statement = connection.createStatement();
        statement.executeQuery("SELECT name FROM country;");
        ResultSet rs = statement.getResultSet();
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }
        statement.close();
    }
}
