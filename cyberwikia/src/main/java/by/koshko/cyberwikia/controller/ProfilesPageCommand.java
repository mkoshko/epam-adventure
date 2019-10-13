package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.service.PlayerService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TeamService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfilesPageCommand extends UserCommand {

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                return new Forward("index.html", true);
            }
            TeamService teamService = factory.getTeamService();
            Team team = teamService.findCreatedTeam(user.getId());
            PlayerService playerService = factory.getPlayerService();
            Player player = playerService.findById(user.getId());
            request.setAttribute("team", team);
            request.setAttribute("player", player);
            return new Forward("WEB-INF/jsp/userpages.jsp");
        } catch (ServiceException e) {
            return sendError(401);
        }
    }
}
