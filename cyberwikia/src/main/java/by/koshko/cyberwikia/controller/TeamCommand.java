package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TeamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TeamCommand extends AbstractCommand {
    private Logger logger = LogManager.getLogger(TeamCommand.class);

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            int teamId = 0;
            try {
                teamId = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                sendError(404);
            }
            TeamService teamService = factory.getTeamService();
            Team team = teamService.loadTeamProfile(teamId);
            if (team == null) {
                sendError(404);
            }
            request.setAttribute("team", team);
            return new Forward("WEB-INF/jsp/team.jsp");
        } catch (ServiceException e) {
            return sendError(500);
        }
    }
}
