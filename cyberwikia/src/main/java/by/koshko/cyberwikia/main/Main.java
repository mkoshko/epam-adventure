package by.koshko.cyberwikia.main;

import by.koshko.cyberwikia.ConnectorDB;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Main {
    public static void main(final String[] args) throws SQLException {
        LocalDate date = LocalDate.of(2009, 05, 21);
        System.out.println(date.toString());
//        ResourceBundle bundle = ResourceBundle.getBundle("database");
//        Connection connection = ConnectorDB.getConnection();
//        Statement statement = connection.createStatement();
//        statement.executeQuery("SELECT name FROM country;");
//        ResultSet rs = statement.getResultSet();
//        rs.next();
//        System.out.println(rs.getString("name"));
//        statement.close();
//        rs.close();
//        connection.close();
//        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 16, 32);
//        String password = "KLmALSK-0#%(_)kpo,m3_)%IJ_)#J%";
//        String hash = argon2.hash(4, 1024*1024, 4, password);
//        System.out.printf("hash length: %d\n", hash.length());
//        System.out.println(argon2.verify(hash, password));
    }
}
