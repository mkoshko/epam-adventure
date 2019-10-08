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
        try (ServiceFactory factory = new ServiceFactory()) {
            int page = 1;
            if (request.getParameter("page") != null) {
                try {
                    page = Integer.parseInt(request.getParameter("page"));
                } catch (NumberFormatException e) {

                }
            }
            TeamService teamService = factory.getTeamService();
            int records = teamService.getRowsNumber();
            List<Team> teams = teamService.findAll(page, 10);
            request.setAttribute("teams", teams);
            request.setAttribute("page", page);
            request.setAttribute("lastPage", records % 10 == 0 ? records / 10 : records / 10 + 1);
            request.getRequestDispatcher("WEB-INF/jsp/teams.jsp")
                    .forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
