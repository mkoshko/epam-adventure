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
import java.io.IOException;

public class LeaveTeamCommand extends UserCommand {
    private Logger logger = LogManager.getLogger(LeaveTeamCommand.class);
    @Override
    public void execute(final HttpServletRequest request,
                        final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()){
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.sendRedirect("team?id=");
                return;
            }
            User user = (User) session.getAttribute("user");
            int teamId = 0;
            try {
                teamId = Integer.parseInt(request.getParameter("team"));
            } catch (NumberFormatException | NullPointerException e) {
                response.sendRedirect("teams.html");
            }
            PlayerTeamService playerTeamService
                    = factory.getPlayerTeamService();
            logger.debug("Leave team status code: {}", playerTeamService.leaveTeam(user.getId()));
            response.sendRedirect("team.html?id=" + teamId);
        } catch (IOException | ServiceException e) {
            e.printStackTrace();
        }
    }
}
