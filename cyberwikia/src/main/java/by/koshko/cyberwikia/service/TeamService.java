package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Team;

public interface TeamService {
    Team findTeamById(long id) throws ServiceException;
}
