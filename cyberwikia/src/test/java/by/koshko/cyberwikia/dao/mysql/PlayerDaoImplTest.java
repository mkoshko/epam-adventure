package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.ConnectorDB;
import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.PlayerDao;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class PlayerDaoImplTest {

    private Connection connection;
    private PlayerDao playerDao;

    @BeforeMethod
    public void setUp() throws SQLException, DaoException {
        connection = ConnectorDB.getConnection();
        playerDao = new PlayerDaoImpl();
        playerDao.setConnection(connection);
    }

    @AfterTest
    public void tearDown() throws SQLException, DaoException {
        Optional<Player> player = playerDao.findByNickname("NiKo");
        player.ifPresent(player1 -> {
            try {
                playerDao.delete(player1);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        });
        connection.close();
    }

    @Test
    public void testFindByNickname() throws DaoException {
        playerDao.setConnection(connection);
        Optional<Player> player = playerDao.findByNickname("dev1ce");
        assertEquals(player.get().getNickname(), "dev1ce");
    }

    @Test
    public void testFindByFullName() throws DaoException {
        playerDao.setConnection(connection);
        List<Player> players = playerDao.findByFullName("Emil Reif");
        assertEquals(players.get(0).getNickname(), "Magisk");
    }

    @Test
    public void testGet() throws DaoException {
        Optional<Player> player = playerDao.get(1);
        assertEquals(player.get().getNickname(), "dev1ce");
    }

    @Test
    public void testGetAll() throws DaoException {
        List<Player> players = playerDao.getAll();
        assertTrue(players.size() > 1);
    }

    @Test
    public void testSave() throws DaoException {
        Player player = new Player();
        player.setNickname("NiKo");
        player.setFirstName("Nikola");
        player.setLastName("Kovač");
        player.setCountry("Bosnia and Herzegovina");
        player.setBirth(LocalDate.of(1997,02,16));
        player.setOverview("test");
        playerDao.save(player);
        Optional<Player> player1 = playerDao.findByNickname("NiKo");
        assertEquals(player1.get(), player);
    }

    @Test(dependsOnMethods = {"testSave"})
    public void testUpdate() throws DaoException {
        Optional<Player> player = playerDao.findByNickname("NiKo");
        System.out.println(player.get());
        player.get().setFirstName("Mikola");
        player.get().setLastName("Kovac");
        System.out.println(player.get());
        playerDao.update(player.get());
        Optional<Player> player1 = playerDao.findByNickname("NiKo");
        assertEquals(player1.get().getFirstName(), "Mikola");
    }

    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() throws DaoException {
        Optional<Player> player = playerDao.findByNickname("NiKo");
        playerDao.delete(player.get());
        Optional<Player> player1 = playerDao.findByNickname("NiKo");
        assertTrue(player1.isEmpty());
    }
}