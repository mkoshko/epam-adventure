package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TeamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TeamsCommand extends AbstractCommand {
    private Logger logger = LogManager.getLogger(TeamsCommand.class);
    private static final int LIMIT = 10;
    @Override
    public Forward execute(final HttpServletRequest request,
                        final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            int page = 1;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            TeamService teamService = factory.getTeamService();
            List<Team> teams = teamService.findAll(page, LIMIT);
            int total = teamService.getRowsNumber();
            request.setAttribute("lastPage", calculateLastPage(total, LIMIT));
            request.setAttribute("teams", teams);
            request.setAttribute("page", page);
            return new Forward("WEB-INF/jsp/teams.jsp");
        } catch (ServiceException e) {
            return sendError(500);
        } catch (NumberFormatException e1) {
            return sendError(404);
        }
    }
}
