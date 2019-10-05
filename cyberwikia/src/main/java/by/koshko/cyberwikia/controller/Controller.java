package by.koshko.cyberwikia.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller {

    private final CommandProvider commandProvider = new CommandProvider();

    public void executeCommand(final HttpServletRequest request,
                               final HttpServletResponse response) {
        String action = (String) request.getAttribute("action");
        Command command = commandProvider.getCommand(action);
        command.execute(request, response);
    }
}
