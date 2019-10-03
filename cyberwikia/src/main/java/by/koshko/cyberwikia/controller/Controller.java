package by.koshko.cyberwikia.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller {
    public void executeCommand(final HttpServletRequest request,
                               final HttpServletResponse response) {
        Object probablyAction = request.getAttribute("action");
        String action;
        if (probablyAction instanceof String) {
            action = (String) probablyAction;
        } else {

        }
    }
}
