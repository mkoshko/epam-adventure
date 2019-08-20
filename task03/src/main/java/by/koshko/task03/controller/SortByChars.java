package by.koshko.task03.controller;

import by.koshko.task03.service.ServiceException;
import by.koshko.task03.service.SortingService;
import by.koshko.task03.service.TextHandler;

public class SortByChars implements Command {
    @Override
    public String execute(final String request) {
        if (request.length() > 1) {
            return "Not a char.";
        }
        try {
            SortingService.sortByCharsNumber(TextHandler.access().getTextComposite(), request.charAt(0));
            return TextHandler.access().showText();
        } catch (ServiceException e) {
            return e.getMessage();
        }
    }
}
