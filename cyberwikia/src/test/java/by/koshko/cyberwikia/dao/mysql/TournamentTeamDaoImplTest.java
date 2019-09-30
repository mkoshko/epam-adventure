package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.bean.Tournament;
import by.koshko.cyberwikia.bean.TournamentTeam;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoFactory;
import by.koshko.cyberwikia.dao.TournamentTeamDao;
import by.koshko.cyberwikia.dao.cyberpool.ConnectionPool;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.testng.Assert.*;

public class TournamentTeamDaoImplTest {

    private TournamentTeamDao ttd;
    private Connection connection;

    @BeforeTest
    public void setUp() throws DaoException {
        ConnectionPool.getInstance().init();
        connection = ConnectionPool.getInstance().getConnection();
        ttd = DaoFactory.getTournamentTeamDao();
        ttd.setConnection(connection);
    }

    @AfterTest
    public void tearDown() throws SQLException {
        connection.close();
        ConnectionPool.getInstance().close();
    }

    @Test
    public void testFindTournamentTeam() {

    }

    @Test
    public void testTestFindTournamentTeam() {
    }

    @Test
    public void testSave() throws DaoException {
        TournamentTeam entity = new TournamentTeam();
        Team team = new Team();
        team.setId(1);
        Tournament tournament = new Tournament();
        tournament.setId(1);
        entity.setTeam(team);
        entity.setTournament(tournament);
        entity.setPlacement(1);
        ttd.save(entity);
    }

    @Test
    public void testUpdate() throws DaoException {
        TournamentTeam entity = new TournamentTeam();
        Team team = new Team();
        team.setId(1);
        Tournament tournament = new Tournament();
        tournament.setId(1);
        entity.setTeam(team);
        entity.setTournament(tournament);
        entity.setPlacement(1);
        ttd.update(entity);
    }

    @Test
    public void testDelete() throws DaoException {
        TournamentTeam entity = new TournamentTeam();
        Team team = new Team();
        team.setId(1);
        Tournament tournament = new Tournament();
        tournament.setId(1);
        entity.setTeam(team);
        entity.setTournament(tournament);
        ttd.delete(entity);
    }
}