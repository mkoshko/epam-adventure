package by.koshko.cyberwikia.service.validation;

import by.koshko.cyberwikia.bean.TournamentTeam;

public class TournamentTeamValidator {
    public static boolean test(final TournamentTeam tournamentTeam) {
        if (tournamentTeam == null
            || tournamentTeam.getTeam() == null
            || tournamentTeam.getTournament() == null) {
            return false;
        }
        if (tournamentTeam.getTeam().getId() <= 0
            || tournamentTeam.getTournament().getId() <= 0) {
            return false;
        }
        if (tournamentTeam.getPlacement() < 0) {
            return false;
        }
        return true;
    }
}
