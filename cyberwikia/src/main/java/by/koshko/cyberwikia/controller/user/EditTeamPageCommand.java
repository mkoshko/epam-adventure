package by.koshko.cyberwikia.controller.user;

import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.controller.UserCommand;
import by.koshko.cyberwikia.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditTeamPageCommand extends UserCommand {

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try (ServiceFactory factory = new ServiceFactory()) {
            User user = (User) session.getAttribute("user");
            TeamService teamService = factory.getTeamService();
            CountryService countryService = factory.getCountryService();
            GameService gameService = factory.getGameService();
            Team team = teamService.findCreatedTeam(user.getId());
            if (team == null) {
                return sendError(404);
            }
            request.setAttribute("team", team);
            request.setAttribute("countries", countryService.getAll());
            request.setAttribute("games", gameService.getAll());
            return new Forward("WEB-INF/jsp/editteam.jsp");
        } catch (ServiceException e) {
            return sendError(500);
        }
    }
}
