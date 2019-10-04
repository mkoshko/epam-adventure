package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Tournament;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.cyberpool.ConnectionPool;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.validation.ValidationFactory;
import by.koshko.cyberwikia.service.validation.ValidationPropertiesLoader;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TournamentServiceImplTest {

    @Test
    public void testSave() throws DaoException, ServiceException {
        ConnectionPool.getInstance().init();
        ValidationPropertiesLoader.loadProperties("validation");

        Tournament tournament = new Tournament();
        tournament.setName("Blast");
    }
}