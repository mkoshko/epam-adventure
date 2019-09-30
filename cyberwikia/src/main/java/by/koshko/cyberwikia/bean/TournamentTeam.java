package by.koshko.cyberwikia.bean;

public final class TournamentTeam extends Entity {

    private Tournament tournament;
    private Team team;
    private int placement;

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(final Tournament tournament) {
        this.tournament = tournament;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(final Team team) {
        this.team = team;
    }

    public int getPlacement() {
        return placement;
    }

    public void setPlacement(final int placement) {
        this.placement = placement;
    }
}
