package by.koshko.task03.controller;

import by.koshko.task03.service.ServiceException;
import by.koshko.task03.service.TextHandler;

/**
 * Command for loading text.
 */
public class LoadTextCommand implements Command {

    /**
     * @param request request from menu.
     * @return response from services.
     */
    @Override
    public String execute(final String request) {
        try {
            TextHandler.access().makeComposite(request);
            return TextHandler.access().showText();
        } catch (ServiceException e) {
            return e.getMessage();
        }
    }
}
