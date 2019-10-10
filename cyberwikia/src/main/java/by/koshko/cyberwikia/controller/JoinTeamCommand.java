package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.service.PlayerTeamService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class JoinTeamCommand extends UserCommand {
    private Logger logger = LogManager.getLogger(JoinTeamCommand.class);
    @Override
    public Forward execute(final HttpServletRequest request,
                        final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            HttpSession session = request.getSession(false);
            int teamId;
            try {
                teamId = Integer.parseInt(request.getParameter("team"));
            } catch (NumberFormatException e) {
                logger.debug("Invalid team id.");
                return new Forward("teams.html");
            }
            User user = (User) session.getAttribute("user");
            PlayerTeamService playerTeamService = factory.getPlayerTeamService();
            logger.debug("Join team status code: {}",
                    playerTeamService.joinTeam(user.getId(), teamId));
            return new Forward("team.html?id=" + teamId);
        } catch (ServiceException e) {
            Forward forward = new Forward(true);
            forward.getAttributes().put("error", 500);
            return forward;
        }
    }
}
