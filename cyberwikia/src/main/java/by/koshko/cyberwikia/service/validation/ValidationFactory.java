package by.koshko.cyberwikia.service.validation;

public class ValidationFactory {
    private static UserValidator userValidator = new UserValidator();
    private static TeamValidator teamValidator = new TeamValidator();
    private static TournamentValidator tournamentValidator
            = new TournamentValidator();
    private static PlayerValidator playerValidator
            = new PlayerValidator();

    public static UserValidator getUserValidator() {
        return userValidator;
    }

    public static TeamValidator getTeamValidator() {
        return teamValidator;
    }

    public static TournamentValidator getTournamentValidator() {
        return tournamentValidator;
    }

    public static PlayerValidator getPlayerValidator() {
        return playerValidator;
    }
}
