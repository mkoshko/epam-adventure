package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.service.PlayerService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller {
    public void executeCommand(final HttpServletRequest request,
                               final HttpServletResponse response) {
        String action = (String) request.getAttribute("action");
    }
}
