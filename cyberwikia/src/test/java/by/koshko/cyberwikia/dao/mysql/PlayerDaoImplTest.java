package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.ConnectorDB;
import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.PlayerDao;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class PlayerDaoImplTest {

    private Connection connection;
    private PlayerDao playerDao;

    @BeforeTest
    public void setUp() throws SQLException, DaoException {
        playerDao = new PlayerDaoImpl();
        connection = ConnectorDB.getConnection();
        playerDao.setConnection(connection);
    }

    @AfterTest
    public void tearDown() throws SQLException, DaoException {
        Optional<Player> pl = playerDao.findByNickname("nickname0");
        playerDao.delete(pl.get());
        pl = playerDao.findByNickname("nickname1");
        playerDao.delete(pl.get());
        connection.close();
    }

    @DataProvider(name = "provider0")
    private Object[][] provide0() {
        return new Object[][] {
                {null, "nickname0", "firstName0", "lastName0", "2000-05-05", Long.valueOf(183), "Overview"},
                {"images/profile/t1.jpg", "nickname1", "firstName1", "lastName1", "1995-07-18", Long.valueOf(183), null}
        };
    }

    @DataProvider(name = "provider1")
    private Object[][] provide1() {
        return new Object[][] {
                {"images/profile/t1.jpg", null, "firstName0", "lastName0", "2000-05-05", Long.valueOf(183), "Overview"},
                {"images/profile/t2.jpg", "nickname1", null, "lastName1", "1995-07-18", Long.valueOf(183), null},
                {"images/profile/t2.jpg", "nickname1", "firstName1", null, "1995-07-18", Long.valueOf(183), null}
        };
    }

    @Test
    public void testFindByNickname() throws DaoException {
        Optional<Player> player = playerDao.findByNickname("flamie");
        assertEquals(player.get().getNickname(), "flamie");
    }

    @Test
    public void testFindByNicknameEmpty() throws DaoException {
        Optional<Player> player = playerDao.findByNickname("");
        assertTrue(player.isEmpty());
    }

    @Test
    public void testFindByNicknameNull() throws DaoException {
        Optional<Player> player = playerDao.findByNickname(null);
        assertTrue(player.isEmpty());
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
        Optional<Player> player = playerDao.get(1);
        assertEquals(player.get().getId(), 1);
    }

    @Test
    public void testGetInvalidId() throws DaoException {
        Optional<Player> player = playerDao.get(0);
        assertTrue(player.isEmpty());
    }

    @Test
    public void testGetInvalidId2() throws DaoException {
        Optional<Player> player = playerDao.get(-1);
        assertTrue(player.isEmpty());
    }

    @Test
    public void testGetAll() throws DaoException {
        List<Player> players = playerDao.getAll();
        assertFalse(players.isEmpty());
    }

    @Test(dataProvider = "provider0", dependsOnMethods = {"testFindByNickname"})
    public void testSave(final String photo, final String nick, final String fName,
                         final String lName, final String birth, final Long cID,
                         final String overview) throws DaoException {
        Player player = new Player();
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
        Optional<Player> player1 = playerDao.findByNickname("nickname0");
        assertFalse(player1.isEmpty());
    }

    @Test(dataProvider = "provider1", dependsOnMethods = {"testFindByNickname"},
    expectedExceptions = DaoException.class)
    public void testSaveInvalidArgs(final String photo, final String nick, final String fName,
                         final String lName, final String birth, final Long cID,
                         final String overview) throws DaoException {
        Player player = new Player();
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
        Optional<Player> player1 = playerDao.findByNickname("Montajnaya");
        player.setId(player1.get().getId());
        player.setNickname("Pojarnaya");
        playerDao.update(player);
        player1 = playerDao.findByNickname("Pojarnaya");
        assertEquals(player1.get(), player);
    }

    @Test(dependsOnMethods = {"testUpdate"})
    public void testDelete() throws DaoException {
        Optional<Player> player = playerDao.findByNickname("Pojarnaya");
        playerDao.delete(player.get());
        player = playerDao.findByNickname("Pojarnaya");
        assertTrue(player.isEmpty());
    }
}