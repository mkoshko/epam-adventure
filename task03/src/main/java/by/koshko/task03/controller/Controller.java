package by.koshko.task03.controller;

/**
 * Class that handle the requests.
 */
public final class Controller {
    /**
     * Instance of this class.
     */
    private static final Controller CONTROLLER = new Controller();
    /**
     * Class from which {@code Command} objects are obtained.
     */
    private static final CommandProvider PROVIDER
            = CommandProvider.getInstance();
    /**
     * Delimiter for splitting request.
     */
    private static final char DELIMITER = ' ';

    /**
     * Private constructor.
     */
    private Controller() {
    }

    /**
     * Returns instance of this class.
     *
     * @return instance of this class.
     */
    public static Controller getInstance() {
        return CONTROLLER;
    }

    /**
     * Handles the request.
     * @param request request to handle.
     * @return response after command execution.
     */
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
