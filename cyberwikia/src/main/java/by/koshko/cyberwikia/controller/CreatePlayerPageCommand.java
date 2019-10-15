package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.service.CountryService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatePlayerPageCommand extends UserCommand {

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            CountryService countryService = factory.getCountryService();
            request.setAttribute("countries", countryService.getAll());
            return new Forward("WEB-INF/jsp/createplayer.jsp");
        } catch (ServiceException e) {
            return sendError(500);
        }
    }
}
