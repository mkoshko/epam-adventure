package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Tournament;

import java.util.List;

public interface TournamentService {
    Tournament getTournamentById(long id) throws ServiceException;
    void createTournament(Tournament tournament) throws ServiceException;
    void updateTournament(Tournament tournament) throws ServiceException;
    void deleteTournament(Tournament tournament) throws ServiceException;
    List<Tournament> findAll(int page, int limit) throws ServiceException;
    int getRowsNumber() throws ServiceException;
}
