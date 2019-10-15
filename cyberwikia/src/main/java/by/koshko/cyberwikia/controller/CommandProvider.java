package by.koshko.cyberwikia.controller;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static final Map<String, AbstractCommand> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("/index", new MainCommand());
        COMMANDS.put("/players", new PlayersCommand());
        COMMANDS.put("/teams", new TeamsCommand());
        COMMANDS.put("/team", new TeamCommand());
        COMMANDS.put("/join", new JoinTeamCommand());
        COMMANDS.put("/leave", new LeaveTeamCommand());
        COMMANDS.put("/signin", new SignInCommand());
        COMMANDS.put("/signout", new SignOutCommand());
        COMMANDS.put("/lang", new LanguageCommand());
        COMMANDS.put("/player", new PlayerProfileCommand());
        COMMANDS.put("/editplayer", new PlayerEditPageCommand());
        COMMANDS.put("/saveplayer", new PlayerEditCommand());
        COMMANDS.put("/registration", new RegistrationPageCommand());
        COMMANDS.put("/signup", new SignUpCommand());
        COMMANDS.put("/mypages", new ProfilesPageCommand());
        COMMANDS.put("/tournament", new TournamentCommand());
        COMMANDS.put("/tournaments", new TournamentsCommand());
        COMMANDS.put("/deleteplayer", new DeletePlayerCommand());
        COMMANDS.put("/createplayer", new CreatePlayerPageCommand());
    }

    public static AbstractCommand getCommand(final String action) {
        return COMMANDS.get(action);
    }
}
