package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TeamService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class TeamProfileCommand implements Command {
    @Override
    public void execute(final HttpServletRequest request, final HttpServletResponse response) {
        try {
            long teamID = Long.parseLong(request.getParameter("id"));
            TeamService teamService = ServiceFactory.getTeamService();
            Team team = teamService.loadTeamProfile(teamID);
            request.setAttribute("team", team);
            request.getRequestDispatcher("team.jsp").forward(request, response);
        } catch (ServiceException e) {
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
