package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.ConnectorDB;
import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.bean.Game;
import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.TeamDao;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static org.testng.Assert.*;

public class TeamDaoImplTest {

    private Connection connection;
    private TeamDao teamDao;

    @BeforeTest
    public void setUp() throws SQLException, DaoException {
        System.out.println("before test");
        connection = ConnectorDB.getConnection();
        teamDao = new TeamDaoImpl();
        teamDao.setConnection(connection);
    }

    @AfterTest
    public void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    public void testFindByName() {
    }

    @Test
    public void testGet() {
    }

    @Test
    public void testGetAll() {
    }

    @Test
    public void testSave() throws DaoException {
        Team team = new Team();
        team.setName("AVANGAR");
        team.setLogoFile("images/team/avangar.png");
        Country country = new Country();
        country.setId(115);
        team.setCountry(country);
        Game game = new Game();
        game.setId(1);
        team.setGame(game);
        Player creator = new Player();
        creator.setId(2);
        team.setCreator(creator);
        team.setOverview("overview");
        teamDao.save(team);
        Optional<Team> team1 = teamDao.findByName("AVANGAR");
        assertEquals(team1.get().getName(), "AVANGAR");
    }

    @Test
    public void testUpdate() {
    }

    @Test(dependsOnMethods = {"testSave"})
    public void testDelete() throws DaoException {
        Optional<Team> team = teamDao.findByName("AVANGAR");
        teamDao.delete(team.get());
        Optional<Team> team1 = teamDao.findByName("AVANGAR");
        assertTrue(team1.isEmpty());
    }
}