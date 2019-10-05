package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Tournament;

public interface TournamentService {
    Tournament getTournamentById(long id) throws ServiceException;
    void createTournament(Tournament tournament) throws ServiceException;
}
