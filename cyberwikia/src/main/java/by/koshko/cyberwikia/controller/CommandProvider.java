package by.koshko.cyberwikia.controller;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put("/players", new PlayersCommand());
    }

    public Command getCommand(final String action) {
        return commands.get(action);
    }
}
