package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TeamService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TeamsCommand implements Command {
    @Override
    public void execute(final HttpServletRequest request,
                        final HttpServletResponse response) {
        try {
            TeamService teamService = ServiceFactory.getTeamService();
            List<Team> teams = teamService.findAll();
            request.setAttribute("teams", teams);
            request.getRequestDispatcher("WEB-INF/jsp/teams.jsp")
                    .forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {

        }
    }
}
