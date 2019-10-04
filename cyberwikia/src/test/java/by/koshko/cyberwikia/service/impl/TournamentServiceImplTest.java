package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Tournament;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.cyberpool.ConnectionPool;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TournamentService;
import by.koshko.cyberwikia.service.validation.ValidationPropertiesLoader;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class TournamentServiceImplTest {

    @Test
    public void testSave() throws DaoException, ServiceException {
        ConnectionPool.getInstance().init();
        ValidationPropertiesLoader.loadProperties("validation");
        TournamentService tournamentService
                = ServiceFactory.getTournamentService();
        Tournament tournament = new Tournament();
        tournament.setName("Blast");
        tournament.setPrize(250000);
        tournament.setOverview("lkasmflkwmeglkmwelg");
        tournament.setStartDate(LocalDate.of(2005, 05, 25));
        tournament.setEndDate(LocalDate.of(2005, 05, 28));
        tournamentService.save(tournament);
    }
}