package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.dao.mysql.TransactionImpl;
import by.koshko.cyberwikia.service.PlayerService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import com.wix.mysql.SqlScriptSource;
import org.testng.annotations.*;

import java.time.LocalDate;
import java.util.List;

import static com.wix.mysql.ScriptResolver.classPathScript;
import static org.testng.Assert.*;

public class PlayerServiceImplTest extends AbstractServiceTest {

    private ServiceFactory factory;
    private PlayerService playerService;
    private static final SqlScriptSource[] SQL_SCRIPT_SOURCES
            = {classPathScript("sql/fill_user.sql"),
               classPathScript("sql/fill_player_table.sql")};

    @BeforeClass
    public void setUp() throws ServiceException {
        factory = new ServiceFactory();
        playerService = factory.getPlayerService();
        database.executeScripts(DATABASE, SQL_SCRIPT_SOURCES);
    }

    @AfterTest
    public void tearDown() {
        factory.close();
    }

    @DataProvider(name = "correct_nickname")
    private Object[][] provide0() {
        return new Object[][]{
                {"dev1ce", "dev1ce"},
                {"DEV1CE", "dev1ce"},
                {"Zeus", "Zeus"},
                {"zeus", "Zeus"},
                {"eus", "Zeus"},
                {"NAF", "NAF"},
                {"AF", "NAF"},
        };
    }

    @Test(dataProvider = "correct_nickname")
    public void testFindPlayersByNickname(final String find,
                                          final String nickname) {
        List<Player> players = playerService.findPlayersByNickname(find);
        assertEquals(players.get(0).getNickname(), nickname);
    }

    @DataProvider(name = "incorrect_nickname")
    private Object[][] provide1() {
        return new Object[][]{
                {null},
                {"SDGKMeg"},
                {""},
                {"<<EG>>"},
                {"      "},
                {" "}
        };
    }

    @Test(dataProvider = "incorrect_nickname")
    public void testFindPlayersByNickname2(final String nickname) {
        List<Player> players = playerService.findPlayersByNickname(nickname);
        assertTrue(players.isEmpty());
    }

    @DataProvider(name = "correct_player")
    private Object[][] provide2() {
        return new Object[][]{
                {26, new Player.Builder("nickname",
                        "firstName", "lastName",
                        LocalDate.parse("1994-05-05"), new Country(1))
                        .setOverview("overview")
                        .setProfilePhoto("image/img.jpg")
                        .build()},
                {27, new Player.Builder("nickname1",
                        "firstName1", "lastName1",
                        LocalDate.parse("1994-05-05"), new Country(55))
                        .build()}
        };
    }

    @Test(dataProvider = "correct_player")
    public void testCreatePlayer(final long userId, final Player player)
            throws ServiceException, DaoException {
        playerService.createPlayer(userId, player);
        Transaction transaction = new TransactionImpl();
        Player player1 = transaction.getPlayerDao().get(userId);
        transaction.close();
        assertNotNull(player1);
    }

    @DataProvider(name = "incorrect_player")
    private Object[][] provide3() {
        return new Object[][]{
                {28, new Player.Builder(null, "firstName", "lastName",
                        LocalDate.parse("2004-01-01"), new Country(1)).build()},
                {28, new Player.Builder("nickName", null, "lastName",
                        LocalDate.parse("2000-01-01"), new Country(1)).build()},
                {28, new Player.Builder("nick", "first", null,
                        LocalDate.parse("2001-01-01"), new Country(1)).build()},
                {28, new Player.Builder("nick", "first", "last", null,
                        new Country(1)).build()},
                {28, new Player.Builder("nick", "first", "last",
                        LocalDate.parse("2001-01-01"), null).build()},
                {28, new Player.Builder("", "", "",
                        LocalDate.parse("2000-01-01"), new Country(1)).build()},
                {28, new Player.Builder("asd", "asd", "asd",
                        LocalDate.parse("2000-01-01"), new Country(555)).build()
                }
        };
    }

    @Test(dataProvider = "incorrect_player")
    public void testCreatePlayerFail(final long userId, final Player player)
            throws ServiceException {
        playerService.createPlayer(userId, player);
        Player player1 = playerService.findById(userId);
        assertNull(player1);
    }

    @DataProvider(name = "correct_id")
    private Object[][] provide4() {
        return new Object[][] {
                {1},
                {2},
                {3}
        };
    }

    @Test(dataProvider = "correct_id")
    public void testGetById(final long id) throws ServiceException {
        Player player = playerService.findById(id);
        assertNotNull(player);
    }

    @DataProvider(name = "invalid_id")
    private Object[][] provide5() {
        return new Object[][] {
                {-1},
                {0},
                {500}
        };
    }

    @Test(dataProvider = "invalid_id")
    public void testGetByIdFail(final long id) throws ServiceException {
        Player player = playerService.findById(id);
        assertNull(player);
    }

    @Test
    public void testGetRowsNumber() throws ServiceException {
        assertEquals(playerService.getRowsNumber(), 24);
    }
}