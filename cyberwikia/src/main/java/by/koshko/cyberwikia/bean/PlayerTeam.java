package by.koshko.cyberwikia.bean;

import java.time.LocalDate;

public final class PlayerTeam extends Entity {

    private static final long serialVersionUID = 1942580934600028473L;
    private Player player;
    private Team team;
    private boolean isActive;
    private LocalDate joinDate;
    private LocalDate leaveDate;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(final Player newPlayer) {
        player = newPlayer;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(final Team newTeam) {
        team = newTeam;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(final boolean active) {
        isActive = active;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(final LocalDate newJoinDate) {
        joinDate = newJoinDate;
    }

    public LocalDate getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(final LocalDate newLeaveDate) {
        leaveDate = newLeaveDate;
    }
}
