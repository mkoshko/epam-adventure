package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.ServiceResponse;
import by.koshko.cyberwikia.bean.Tournament;

import java.util.List;

public interface TournamentService {
    Tournament getTournamentById(long id) throws ServiceException;
    ServiceResponse createTournament(Tournament tournament) throws ServiceException;
    ServiceResponse updateTournament(Tournament tournament) throws ServiceException;
    void deleteTournament(long id) throws ServiceException;
    List<Tournament> findAll(int page, int limit) throws ServiceException;
    int getRowsNumber() throws ServiceException;
    List<Tournament> findByName(String name);
    List<Tournament> findUpcoming(int limit);
    List<Tournament> findOngoing(int limit);
}
