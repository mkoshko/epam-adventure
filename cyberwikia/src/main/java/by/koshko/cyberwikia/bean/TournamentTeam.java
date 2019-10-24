package by.koshko.cyberwikia.bean;

public final class TournamentTeam extends Entity {

    private static final long serialVersionUID = 6458680934600028453L;
    private Tournament tournament;
    private Team team;
    private int placement;

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(final Tournament newTournament) {
        tournament = newTournament;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(final Team newTeam) {
        team = newTeam;
    }

    public int getPlacement() {
        return placement;
    }

    public void setPlacement(final int newPlacement) {
        placement = newPlacement;
    }
}
