package by.koshko.cyberwikia.bean;

import java.time.LocalDate;

public class PlayerTeam extends Entity {
    private Player player;
    private Team team;
    private boolean active;
    private LocalDate joinDate;
    private LocalDate leaveDate;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(final Player player) {
        this.player = player;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(final Team team) {
        this.team = team;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(final LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public LocalDate getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(final LocalDate leaveDate) {
        this.leaveDate = leaveDate;
    }
}
