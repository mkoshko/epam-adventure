package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Game;
import by.koshko.cyberwikia.dao.pool.ConnectionPool;
import by.koshko.cyberwikia.service.GameService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class GameServiceImplTest extends AbstractServiceTest {

    private ServiceFactory factory;
    private GameService gameService;
    private final int numberOfGames = 4;

    @BeforeClass
    public void setUp() throws ServiceException {
        factory = new ServiceFactory();
        gameService = factory.getGameService();
    }

    @AfterClass
    public void tearDown() {
        factory.close();
    }

    @DataProvider(name = "id_provider")
    private Object[][] provideId() {
        return new Object[][] {
                {1, "Counter-Strike Global Offensive"},
                {2, "Dota 2"},
                {3, "Apex Legends"},
                {4, "Rainbow Six Siege"}
        };
    }

    @DataProvider(name = "id_provider_incorrect")
    private Object[][] provideIdIncorrect() {
        return new Object[][] {
                {-1},
                {0},
                {5}
        };
    }

    @Test(dataProvider = "id_provider")
    public void testFindById(final int id, final String title)
            throws ServiceException {
        Game game = gameService.findById(id);
        assertEquals(game.getTitle(), title);
    }

    @Test(dataProvider = "id_provider_incorrect")
    public void testFindById2(final int id)
            throws ServiceException {
        Game game = gameService.findById(id);
        assertNull(game);
    }

    @Test
    public void testGetAll() throws ServiceException {
        List<Game> games = gameService.getAll();
        assertEquals(games.size(), numberOfGames);
    }
}