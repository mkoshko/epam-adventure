package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.PlayerTeam;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.PlayerTeamDao;
import by.koshko.cyberwikia.dao.cyberpool.ConnectionPool;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.testng.Assert.*;

public class PlayerTeamDaoImplTest {

    private Connection connection;
    private PlayerTeamDao playerTeamDao;

    @BeforeTest
    public void setUp() throws DaoException, SQLException {
        ConnectionPool.access().init();
        connection = ConnectionPool.access().getConnection();
        playerTeamDao = new PlayerTeamDaoImpl();
        playerTeamDao.setConnection(connection);
    }

    @AfterTest
    public void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    public void testFindPlayerTeam() throws DaoException {
        Team team = new Team();
        team.setId(1);
        List<PlayerTeam> playerTeams = playerTeamDao.findPlayerTeam(team);
        assertEquals(playerTeams.size(), 6);
    }

    @Test
    public void testTestFindPlayerTeam() {
    }

    @Test
    public void testSave() throws DaoException {
        Player player = new Player();
        player.setId(1);
        Team team = new Team();
        team.setId(1);
        PlayerTeam playerTeam = new PlayerTeam();
        playerTeam.setPlayer(player);
        playerTeam.setTeam(team);
        playerTeam.setJoinDate(LocalDate.now());
        playerTeam.setActive(true);
        playerTeamDao.save(playerTeam);
    }

    @Test
    public void testUpdate() throws DaoException {
        Player player = new Player();
        player.setId(1);
        List<PlayerTeam> playerTeam
                = playerTeamDao.findPlayerTeam(player);
        playerTeam.get(0).setActive(false);
        playerTeam.get(0).setLeaveDate(LocalDate.now());
        playerTeamDao.update(playerTeam.get(0));
    }

    @Test
    public void testDelete() {
    }
}