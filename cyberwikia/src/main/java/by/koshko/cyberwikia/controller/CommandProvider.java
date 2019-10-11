package by.koshko.cyberwikia.controller;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<String, AbstractCommand> commands = new HashMap<>();

    public CommandProvider() {
        commands.put("/index", new MainCommand());
        commands.put("/players", new PlayersCommand());
        commands.put("/teams", new TeamsCommand());
        commands.put("/team", new TeamCommand());
        commands.put("/join", new JoinTeamCommand());
        commands.put("/leave", new LeaveTeamCommand());
        commands.put("/signin", new SignInCommand());
        commands.put("/signout", new SignOutCommand());
        commands.put("/lang", new LanguageCommand());
        commands.put("/player", new PlayerProfileCommand());
        commands.put("/editprofile", new PlayerEditPageCommand());
        commands.put("/registration", new RegistrationPageCommand());
        commands.put("/signup", new SignUpCommand());
    }

    public AbstractCommand getCommand(final String action) {
        return commands.get(action);
    }
}
