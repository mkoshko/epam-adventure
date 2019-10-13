package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.service.CountryService;
import by.koshko.cyberwikia.service.PlayerService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PlayerEditPageCommand extends UserCommand {

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            PlayerService playerService = factory.getPlayerService();
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            if (user == null) {
                return sendError(404);
            }
            Player player = playerService.findById(user.getId());
            if (player == null) {
                return sendError(404);
            }
            CountryService countryService = factory.getCountryService();
            List<Country> countries = countryService.getAll();
            request.setAttribute("countries", countries);
            request.setAttribute("player", player);
            return new Forward("WEB-INF/jsp/editplayer.jsp");
        } catch (ServiceException e) {
            return sendError(500);
        }
    }
}
