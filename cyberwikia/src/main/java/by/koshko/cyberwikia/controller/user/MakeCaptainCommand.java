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

public final class MakeCaptainCommand extends UserCommand {

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        HttpSession session = request.getSession();
        try (ServiceFactory factory = new ServiceFactory()) {
            User user = (User) session.getAttribute("user");
            long playerId = Long.parseLong(request.getParameter("id"));
            TeamService teamService = factory.getTeamService();
            ServiceResponse serviceResponse
                    = teamService.setTeamCaptain(user.getId(), playerId);
            if (!serviceResponse.hasErrors()) {
                return sendBack(request);
            } else {
                return sendError(400);
            }
        } catch (NumberFormatException e) {
            return sendError(400);
        } catch (ServiceException e1) {
            return sendError(500);
        }
    }
}
