package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.ServiceResponse;
import by.koshko.cyberwikia.bean.Team;

import java.util.List;

public interface TeamService {
    ServiceResponse setTeamCaptain(long userId, long playerId);
    ServiceResponse setTeamCoach(long userId, long playerId);
    ServiceResponse updateTeam(long userId, Team team) throws ServiceException;
    Team findTeamById(long id) throws ServiceException;
    Team loadTeamProfile(long id) throws ServiceException;
    ServiceResponse createTeam(long userId, Team team) throws ServiceException;
    List<Team> findAll() throws ServiceException;
    List<Team> findAll(int page, int limit) throws ServiceException;
    int getRowsNumber() throws ServiceException;
    Team findCreatedTeam(long userId) throws ServiceException;
    ServiceResponse deleteTeam(long userId);
}
