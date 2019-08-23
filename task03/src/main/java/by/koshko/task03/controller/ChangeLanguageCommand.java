package by.koshko.task03.controller;

import by.koshko.task03.service.LocaleManagementService;

/**
 * Command for changing language.
 */
public class ChangeLanguageCommand implements Command {

    /**
     * @param request request from view.
     * @return response from services.
     */
    @Override
    public String execute(final String request) {
        String[] args = request.split("_");
        LocaleManagementService.changeLocale(args[0], args[1]);
        return "Done";
    }
}
