package by.koshko.cyberwikia;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.jdbc.MysqlDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConnectorDB {

    public static Connection getConnection() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        ResourceBundle bundle = ResourceBundle.getBundle("database");
        Properties prop = new Properties();
        String url = bundle.getString("db.url");
        String user = bundle.getString("db.user");
        String pass = bundle.getString("db.pass");
        prop.put("user", user);
        prop.put("password", pass);
        dataSource.setURL(url);
        return dataSource.getConnection(user, pass);
    }
}
