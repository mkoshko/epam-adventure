package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Team;

import java.util.List;

public interface TeamService {
    Team findTeamById(long id) throws ServiceException;
    Team loadTeamProfile(long id) throws ServiceException;
    void createTeam(Team team) throws ServiceException;
    List<Team> findAll() throws ServiceException;
}
