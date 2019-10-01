package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoFactory;
import by.koshko.cyberwikia.dao.TeamDao;
import by.koshko.cyberwikia.dao.cyberpool.ConnectionPool;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TeamDaoImplTest {

    @BeforeMethod
    public void setUp() throws DaoException {
        ConnectionPool.getInstance().init();
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testGet() throws DaoException {
        TeamDao teamDao = DaoFactory.getTeamDao();
        teamDao.setConnection(ConnectionPool.getInstance().getConnection());
        Team team = teamDao.get(3);
        System.out.println(team.getCoach());
        System.out.println(team.getCaptain());
        System.out.println(team.getCreator());
    }
}