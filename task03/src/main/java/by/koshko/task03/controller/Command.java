package by.koshko.task03.controller;

/**
 * Command pattern.
 */
public interface Command {
    /**
     * Perform some actions depends on request.
     *
     * @param request request from menu.
     * @return response after command execution.
     */
    String execute(String request);
}
