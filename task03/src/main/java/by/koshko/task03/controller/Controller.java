package by.koshko.task03.controller;

public class Controller {
    private static final Controller CONTROLLER = new Controller();
    private static final CommandProvider PROVIDER
            = CommandProvider.getInstance();
    private static final char DELIMITER = ' ';

    private Controller() {
    }

    public static Controller getInstance() {
        return CONTROLLER;
    }

    public String executeTask(final String request) {
        var commandName = request.substring(0, request.indexOf(DELIMITER));
        var executionCommand = PROVIDER.getCommand(commandName);
        if (executionCommand != null) {
            return executionCommand.
                    execute(request.substring(request.indexOf(DELIMITER) + 1));
        } else {
            return "Unknown command";
        }
    }
}
