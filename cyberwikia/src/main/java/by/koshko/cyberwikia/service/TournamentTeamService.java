package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.bean.TournamentTeam;

import java.util.List;

public interface TournamentTeamService {
    List<TournamentTeam> findTournamentsForTeam(Team team)
            throws ServiceException;
    List<TournamentTeam> findTournamentsForPlayer(Player player)
            throws ServiceException;
    void addTournamentTeam(TournamentTeam tournamentTeam)
            throws ServiceException;
    void updateTournamentTeam(TournamentTeam tournamentTeam)
            throws ServiceException;
}
