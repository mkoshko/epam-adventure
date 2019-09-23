package by.koshko.cyberwikia.main;

import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.cyberpool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(final String[] args) throws DaoException, InterruptedException {
        ConnectionPool connectionPool = ConnectionPool.access();
        connectionPool.init();
        List<Connection> connections = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 16; i++) {
            service.execute(() -> {
                try {
                    Connection connection = connectionPool.getConnection();
                    System.out.println(Thread.currentThread().getName() + " got a connection");
                    TimeUnit.MILLISECONDS.sleep(5000);
                    connection.close();
                    System.out.println(Thread.currentThread().getName() + " release a connection");
                } catch (DaoException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
    }
}