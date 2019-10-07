package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TeamService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TeamCommand implements Command {
    @Override
    public void execute(final HttpServletRequest request,
                        final HttpServletResponse response) {
        try {
            int teamId = Integer.parseInt(request.getParameter("id"));
            TeamService teamService = ServiceFactory.getTeamService();
            Team team = teamService.loadTeamProfile(teamId);
            if (team == null) {
                response.sendRedirect("index.html");
            }
            request.setAttribute("team", team);
            request.getRequestDispatcher("WEB-INF/jsp/team.jsp")
                    .forward(request, response);
        } catch (ServiceException | IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}
