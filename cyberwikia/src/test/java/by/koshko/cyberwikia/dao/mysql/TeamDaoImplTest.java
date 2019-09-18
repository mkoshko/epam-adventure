package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.ConnectorDB;
import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.PlayerDao;
import by.koshko.cyberwikia.dao.TeamDao;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class TeamDaoImplTest {

    private PlayerDao playerDao;
    private TeamDao teamDao;
    private Connection connection;

    @BeforeMethod
    public void setUp() throws SQLException, DaoException {
        connection = ConnectorDB.getConnection();
        teamDao = new TeamDaoImpl();
        playerDao = new PlayerDaoImpl();
        playerDao.setConnection(connection);
        teamDao.setConnection(connection);
    }

    @AfterMethod
    public void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    public void testFindByName() throws DaoException {
        Optional<Team> team = teamDao.findByName("Astralis");
        assertEquals(team.get().getName(), "Astralis");
    }

    @Test
    public void testGet() throws DaoException {
        Optional<Team> team = teamDao.get(1);
        assertTrue(team.isPresent());
    }

    @Test
    public void testGetAll() throws DaoException {
        List<Team> teams = teamDao.getAll();
        System.out.println(teams.size());
    }

    @Test
    public void testSave() throws DaoException {
        Team team = new Team();
        team.setName("NaVi");
        team.setLocation("Ukraine");
        team.setCreator("flamie");
        team.setCaptain("flamie");
        team.setGame("Counter-Strike Global Offensive");
        team.setOverview("NAVI DAVI");
        try {
            teamDao.save(team);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        Optional<Team> team1 = teamDao.findByName("NaVi");
        assertEquals(team.getName(), team1.get().getName());
    }

    @Test
    public void testUpdate() throws DaoException {
        Optional<Team> team = teamDao.get(1);
        team.get().setName("Astralis");
        teamDao.update(team.get());
        team = teamDao.get(1);
        assertEquals(team.get().getCoach(), "zonic");
    }

    @Test(dependsOnMethods = "testSave")
    public void testDelete() throws DaoException {
        Optional<Team> team = teamDao.findByName("NaVi");
        teamDao.delete(team.get());
        assertTrue(teamDao.findByName("NaVi").isEmpty());
    }

    @Test
    public void testAddPlayer() throws DaoException {
        Optional<Player> player = playerDao.findByNickname("zonic");
        Optional<Team> team = teamDao.findByName("Astralis");
        teamDao.addPlayer(player.get(), team.get());
    }

    @Test
    public void testDisbandPlayer() throws DaoException {
        Optional<Player> player = playerDao.findByNickname("zonic");
        Optional<Team> team = teamDao.findByName("Astralis");
        teamDao.disbandPlayer(player.get(), team.get());
    }
}