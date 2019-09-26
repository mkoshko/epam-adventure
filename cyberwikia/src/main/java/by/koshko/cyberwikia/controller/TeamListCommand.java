package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.impl.TeamServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public final class TeamListCommand implements Command {
    @Override
    public void execute(final HttpServletRequest request, final HttpServletResponse response) {
        try {
            TeamServiceImpl ts = new TeamServiceImpl();
            List<Team> teams = ts.findAll();
            request.setAttribute("teams", teams);
            request.getRequestDispatcher("teams.jsp").forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
