package by.koshko.task03.controller;

import by.koshko.task03.service.ServiceException;
import by.koshko.task03.service.SortingService;
import by.koshko.task03.service.TextHandler;

/**
 * Command for sorting text.
 */
public class SortByWordNumber implements Command {
    /**
     * @param request request from menu.
     * @return response from services.
     */
    @Override
    public String execute(final String request) {
        try {
            SortingService.sortByWordsNumber(TextHandler.access()
                    .getTextComposite());
            return TextHandler.access().showText();
        } catch (ServiceException e) {
            return e.getMessage();
        }
    }
}
