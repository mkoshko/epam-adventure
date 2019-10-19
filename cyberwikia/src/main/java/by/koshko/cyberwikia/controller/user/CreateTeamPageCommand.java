package by.koshko.cyberwikia.controller.user;

import by.koshko.cyberwikia.controller.UserCommand;
import by.koshko.cyberwikia.service.CountryService;
import by.koshko.cyberwikia.service.GameService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class CreateTeamPageCommand extends UserCommand {

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            CountryService countryService = factory.getCountryService();
            GameService gameService = factory.getGameService();
            request.setAttribute("countries", countryService.getAll());
            request.setAttribute("games", gameService.getAll());
            return new Forward("WEB-INF/jsp/createteam.jsp");
        } catch (ServiceException e) {
            return sendError(500);
        }
    }
}
