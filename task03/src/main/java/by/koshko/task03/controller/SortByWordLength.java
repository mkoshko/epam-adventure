package by.koshko.task03.controller;

import by.koshko.task03.service.ServiceException;
import by.koshko.task03.service.SortingService;
import by.koshko.task03.service.TextHandler;

public class SortByWordLength implements Command {
    @Override
    public String execute(final String request) {
        try {
            SortingService.sortByWordsLength(TextHandler.access().getTextComposite());
            return TextHandler.access().showText();
        } catch (ServiceException e) {
            return e.getMessage();
        }
    }
}
