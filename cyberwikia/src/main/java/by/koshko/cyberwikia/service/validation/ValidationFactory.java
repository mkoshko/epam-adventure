package by.koshko.cyberwikia.service.validation;

public class ValidationFactory {
    private static UserValidator userValidator = new UserValidator();
    private static TeamValidator teamValidator = new TeamValidator();

    public static UserValidator getUserValidator() {
        return userValidator;
    }
    public static TeamValidator getTeamValidator() {
        return teamValidator;
    }
}
