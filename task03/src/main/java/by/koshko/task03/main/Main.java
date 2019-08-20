package by.koshko.task03.main;


import by.koshko.task03.controller.Runner;
import by.koshko.task03.dao.DaoException;
import by.koshko.task03.service.ServiceException;

public class Main {
    public static void main(final String[] args) throws DaoException, ServiceException {
        Runner.start();
    }
}
