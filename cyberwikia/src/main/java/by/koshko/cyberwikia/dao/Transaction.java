package by.koshko.cyberwikia.dao;

public interface Transaction {
    CountryDao getCountryDao();
    GameDao getGameDao();
    UserDao getUserDao();
    PlayerDao getPlayerDao();
    TeamDao getTeamDao();
    TournamentDao getTournamentDao();
    PlayerTeamDao getPlayerTeamDao();
    TournamentTeamDao getTournamentTeamDao();
    void close();
}
