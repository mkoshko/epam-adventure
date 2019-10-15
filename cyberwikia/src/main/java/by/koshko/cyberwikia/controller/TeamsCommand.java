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
            TeamService teamService = factory.getTeamService();
            int total = teamService.getRowsNumber();
            int page = Pagination.makePagination(request, total);
            if (page > 0) {
                List<Team> teams = teamService.findAll(page, LIMIT);
                request.setAttribute("teams", teams);
                return new Forward("WEB-INF/jsp/teams.jsp");
            } else {
                return sendError(404);
            }
        } catch (ServiceException e) {
            return sendError(500);
        }
    }
}
