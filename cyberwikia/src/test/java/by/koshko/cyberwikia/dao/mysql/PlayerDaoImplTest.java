package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.PlayerDao;
import by.koshko.cyberwikia.dao.cyberpool.ConnectionPool;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.testng.Assert.*;

public class PlayerDaoImplTest {

    private Connection connection;
    private PlayerDao playerDao;

    @BeforeTest
    public void setUp() throws SQLException, DaoException {
        playerDao = new PlayerDaoImpl();
        ConnectionPool.getInstance().init();
        connection = ConnectionPool.getInstance().getConnection();
        playerDao.setConnection(connection);
    }

    @AfterTest
    public void tearDown() throws SQLException, DaoException {
        Player player = playerDao.findByNickname("nickname0");
        playerDao.delete(player);
        player = playerDao.findByNickname("nickname1");
        playerDao.delete(player);
        connection.close();
    }

    @DataProvider(name = "provider0")
    private Object[][] provide0() {
        return new Object[][] {
                {1, null, "nickname0", "firstName0", "lastName0", "2000-05-05", Long.valueOf(183), "Overview"},
                {2, "images/profile/t1.jpg", "nickname1", "firstName1", "lastName1", "1995-07-18", Long.valueOf(183), null}
        };
    }

    @DataProvider(name = "provider1")
    private Object[][] provide1() {
        return new Object[][] {
                {1, "images/profile/t1.jpg", null, "firstName0", "lastName0", "2000-05-05", Long.valueOf(183), "Overview"},
                {2, "images/profile/t2.jpg", "nickname1", null, "lastName1", "1995-07-18", Long.valueOf(183), null},
                {3, "images/profile/t2.jpg", "nickname1", "firstName1", null, "1995-07-18", Long.valueOf(183), null}
        };
    }

    @Test
    public void testFindByNickname() throws DaoException {
        Player player = playerDao.findByNickname("flamie");
        assertEquals(player.getNickname(), "flamie");
    }

    @Test
    public void testFindByNicknameEmpty() throws DaoException {
        Player player = playerDao.findByNickname("");
        assertNull(player);
    }

    @Test
    public void testFindByNicknameNull() throws DaoException {
        Player player = playerDao.findByNickname(null);
        assertNull(player);
    }

    @Test
    public void getAllTest() throws DaoException {
        List<Player> players = playerDao.getAll(5, 5);
        assertEquals(players.size(), 5);
        System.out.println(players);
    }

    @Test
    public void testFindByFullName() throws DaoException {
        List<Player> players = playerDao.findByFullName("Egor Vasilev");
        String fullName = players.get(0).getFirstName()
                          + " " + players.get(0).getLastName();
        assertEquals(fullName, "Egor Vasilev");
    }

    @Test
    public void testFindByFullNameEmpty() throws DaoException {
        List<Player> players = playerDao.findByFullName("");
        assertTrue(players.isEmpty());
    }

    @Test
    public void testFindByFullNameNull() throws DaoException {
        List<Player> players = playerDao.findByFullName(null);
        assertTrue(players.isEmpty());
    }

    @Test
    public void testGet() throws DaoException {
        Player player = playerDao.get(1);
        assertEquals(player.getId(), 1);
    }

    @Test
    public void testGetInvalidId() throws DaoException {
        Player player = playerDao.get(0);
        assertNull(player);
    }

    @Test
    public void testGetAll() throws DaoException {
        List<Player> players = playerDao.getAll();
        assertFalse(players.isEmpty());
    }

    @Test(dataProvider = "provider0", dependsOnMethods = {"testFindByNickname"})
    public void testSave(final long id, final String photo, final String nick, final String fName,
                         final String lName, final String birth, final Long cID,
                         final String overview) throws DaoException {
        Player player = new Player();
        player.setId(id);
        player.setProfilePhoto(photo);
        player.setNickname(nick);
        player.setFirstName(fName);
        player.setLastName(lName);
        player.setBirth(LocalDate.parse(birth));
        Country c = new Country();
        c.setId(cID);
        player.setCountry(c);
        player.setOverview(overview);
        playerDao.save(player);
        Player player1 = playerDao.findByNickname("nickname0");
        assertNotNull(player1);
    }

    @Test(dataProvider = "provider1", dependsOnMethods = {"testFindByNickname"},
    expectedExceptions = DaoException.class)
    public void testSaveInvalidArgs(final long id, final String photo, final String nick, final String fName,
                         final String lName, final String birth, final Long cID,
                         final String overview) throws DaoException {
        Player player = new Player();
        player.setId(id);
        player.setProfilePhoto(photo);
        player.setNickname(nick);
        player.setFirstName(fName);
        player.setLastName(lName);
        player.setBirth(LocalDate.parse(birth));
        Country c = new Country();
        c.setId(cID);
        player.setCountry(c);
        player.setCountry(c);
        player.setOverview(overview);
        playerDao.save(player);
    }

    @Test(dependsOnMethods = {"testSave", "testFindByNickname"})
    public void testUpdate() throws DaoException {
        Player player = new Player();
        player.setFirstName("Gena");
        player.setNickname("Montajnaya");
        player.setLastName("Pena");
        player.setBirth(LocalDate.parse("1999-10-10"));
        Country c = new Country();
        c.setId(183);
        player.setCountry(c);
        playerDao.save(player);
        Player player1 = playerDao.findByNickname("Montajnaya");
        player.setId(player1.getId());
        player.setNickname("Pojarnaya");
        playerDao.update(player);
        player1 = playerDao.findByNickname("Pojarnaya");
        assertEquals(player1, player);
    }

    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() throws DaoException {
        Player player = playerDao.findByNickname("Pojarnaya");
        playerDao.delete(player);
        player = playerDao.findByNickname("Pojarnaya");
        assertNull(player);
    }
}