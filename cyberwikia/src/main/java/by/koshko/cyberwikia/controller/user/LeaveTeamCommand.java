package by.koshko.cyberwikia.controller.user;

import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.controller.UserCommand;
import by.koshko.cyberwikia.service.PlayerTeamService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LeaveTeamCommand extends UserCommand {
    private Logger logger = LogManager.getLogger(LeaveTeamCommand.class);

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        Forward forward = new Forward();
        try (ServiceFactory factory = new ServiceFactory()) {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            int teamId = 0;
            try {
                teamId = Integer.parseInt(request.getParameter("team"));
            } catch (NumberFormatException | NullPointerException e) {
                forward = new Forward();
                forward.setError(true);
                forward.getAttributes().put("error", 404);
                return forward;
            }
            PlayerTeamService playerTeamService
                    = factory.getPlayerTeamService();
            logger.debug("Leave team status code: {}",
                    playerTeamService.leaveTeam(user.getId()));
            forward.setUrl("team.html?id=" + teamId);
            return forward;
        } catch (ServiceException e) {
            forward.setError(true);
            forward.getAttributes().put("error", 500);
            return forward;
        }
    }
}
