package by.koshko.cyberwikia.controller.user;

import by.koshko.cyberwikia.bean.ServiceResponse;
import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.controller.UserCommand;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TeamService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteTeamCommand extends UserCommand {

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        HttpSession session = request.getSession();
        try (ServiceFactory factory = new ServiceFactory()) {
            User user = (User) session.getAttribute("user");
            TeamService teamService = factory.getTeamService();
            ServiceResponse serviceResponse
                    = teamService.deleteTeam(user.getId());
            return makeForward("mypages.html",
                    "mypages.html", serviceResponse, session);
        } catch (ServiceException e) {
            return sendError(500);
        }
    }
}
