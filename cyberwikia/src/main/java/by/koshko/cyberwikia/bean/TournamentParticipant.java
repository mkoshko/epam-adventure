package by.koshko.cyberwikia.bean;

public final class TournamentParticipant extends Entity {

    private Team team;
    private int placement;

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
