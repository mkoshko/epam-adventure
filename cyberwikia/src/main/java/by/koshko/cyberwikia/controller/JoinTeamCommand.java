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

public class JoinTeamCommand implements Command {
    private Logger logger = LogManager.getLogger(JoinTeamCommand.class);
    @Override
    public void execute(final HttpServletRequest request,
                        final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            HttpSession session = request.getSession(false);
            int teamId = Integer.parseInt(request.getParameter("team"));
            if (session == null) {
                response.sendRedirect("team.html?id=" + teamId);
                return;
            }
            User user = (User) session.getAttribute("user");
            PlayerTeamService playerTeamService = factory.getPlayerTeamService();
            logger.debug("Join team status code: {}", playerTeamService.joinTeam(user.getId(), teamId));
            response.sendRedirect("team.html?id=" + teamId);
        } catch (IOException | ServiceException e) {
            e.printStackTrace();
        }
    }
}
