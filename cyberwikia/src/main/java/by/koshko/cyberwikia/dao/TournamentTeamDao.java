package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.bean.Tournament;
import by.koshko.cyberwikia.bean.TournamentTeam;

import java.util.List;

public interface TournamentTeamDao extends Dao<TournamentTeam> {

    List<TournamentTeam> findTournamentTeam(Team team) throws DaoException;
    List<TournamentTeam> findTournamentTeam(Tournament tournament)
            throws DaoException;
    List<TournamentTeam> findTournamentTeam(Player player)throws DaoException;
    List<Long> getTopTeams(int limit) throws DaoException;
}
