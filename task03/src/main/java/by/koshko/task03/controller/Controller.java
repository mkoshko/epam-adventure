package by.koshko.task03.controller;

public class Controller {
    private final CommandProvider provider = new CommandProvider();
    private static final char DELIMITER = ' ';

    public String executeTask(final String request) {
        var commandName = request.substring(0, request.indexOf(DELIMITER));
        var executionCommand = provider.getCommand(commandName);
        if (executionCommand != null) {
            return executionCommand.
                    execute(request.substring(request.indexOf(DELIMITER) + 1));
        } else {
            return "Unknown command";
        }
    }
}
