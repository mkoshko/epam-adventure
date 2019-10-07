package by.koshko.cyberwikia.controller;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put("/", new MainCommand());
        commands.put("/index", commands.get("/"));
        commands.put("/players", new PlayersCommand());
        commands.put("/teams", new TeamsCommand());
        commands.put("/team", new TeamCommand());
        commands.put("/join", new JoinTeamCommand());
        commands.put("/leave", new LeaveTeamCommand());
        commands.put("/signin", new SignInCommand());
        commands.put("/signout", new SignOutCommand());
        commands.put("/lang", new LanguageCommand());
    }

    public Command getCommand(final String action) {
        return commands.get(action);
    }
}
