package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.PlayerTeam;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.bean.TournamentTeam;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.TeamDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.koshko.cyberwikia.dao.DaoTypes.TEAMDAO;

public class TeamServiceImpl extends AbstractService implements TeamService {

    private Logger logger = LogManager.getLogger(TeamServiceImpl.class);

    public TeamServiceImpl() throws ServiceException {
        super();
    }

    public TeamServiceImpl(final Transaction transaction) {
        super(transaction);
    }

    public Team loadTeamProfile(final long id) throws ServiceException {
        Transaction transaction = getTransaction();
        if (id <= 0) {
            return null;
        }
        try {
            TeamDao teamDao = transaction.getDao(TEAMDAO);
            Team team = teamDao.get(id);
            if (team == null) {
                return null;
            }
            CountryService countryService
                    = ServiceFactory.getCountryService(transaction);
            GameService gameService
                    = ServiceFactory.getGameService(transaction);
            PlayerService playerService
                    = ServiceFactory.getPlayerService(transaction);
            TournamentTeamService tournamentTeamService
                    = ServiceFactory.getTournamentTeamService(transaction);
            PlayerTeamService playerTeamService
                    = ServiceFactory.getPlayerTeamService(transaction);
            long countryID = team.getCountry().getId();
            team.setCountry(countryService.getCountryById(countryID));
            long gameID = team.getGame().getId();
            team.setGame(gameService.findById(gameID));
            Player creator = team.getCreator();
            Player captain = team.getCaptain();
            Player coach = team.getCoach();
            if (creator != null) {
                creator = playerService.findById(creator.getId());
                team.setCreator(creator);
            }
            if (captain != null) {
                captain = playerService.findById(captain.getId());
                team.setCaptain(captain);
            }
            if (coach != null) {
                coach = playerService.findById(coach.getId());
                team.setCoach(coach);
            }
            List<TournamentTeam> tournaments = tournamentTeamService
                    .findTournamentsForTeam(team);
            team.setTournaments(tournaments);
            List<PlayerTeam> players = playerTeamService.loadTeamPlayers(team, true);
            team.setPlayers(players);
            return team;
        } catch (DaoException e) {
            throw new ServiceException("Cannot load team profile.");
        } finally {
            close();
        }
    }

    public Team findTeamById(final long id) throws ServiceException {
        try {
            TeamDao teamDao = getTransaction().getDao(TEAMDAO);
            GameService gs = ServiceFactory.getGameService(getTransaction());
            PlayerService ps = ServiceFactory.getPlayerService(getTransaction());
            CountryService cs = ServiceFactory.getCountryService(getTransaction());
            PlayerTeamService pts = ServiceFactory.getPlayerTeamService(getTransaction());
            Team team = teamDao.get(id);
            team.setGame(gs.findById(team.getGame().getId()));
            team.setCountry(cs.getCountryById(team.getCountry().getId()));
            team.setPlayers(pts.loadTeamPlayers(team, true));
            team.setCaptain(ps.findById(team.getCaptain().getId()));
            team.setCoach(ps.findById(team.getCoach().getId()));
            for (int i = 0; i < team.getPlayers().size(); i++) {
                long idx = team.getPlayers().get(i).getPlayer().getId();
                team.getPlayers().get(i).setPlayer(ps.findById(idx));
            }
            return team;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        } finally {
            close();
        }
    }

    public List<Team> findAll() throws ServiceException {
        try {
            TeamDao teamDao = getTransaction().getDao(TEAMDAO);
            return teamDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        } finally {
            close();
        }
    }

}
