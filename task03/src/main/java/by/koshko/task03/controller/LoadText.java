package by.koshko.task03.controller;

import by.koshko.task03.service.ServiceException;
import by.koshko.task03.service.TextHandler;

public class LoadText implements Command {
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
