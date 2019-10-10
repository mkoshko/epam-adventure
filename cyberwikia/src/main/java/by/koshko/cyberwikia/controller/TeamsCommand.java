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
                try {
                    page = Integer.parseInt(request.getParameter("page"));
                } catch (NumberFormatException e) {
                    logger.debug("Invalid page.");
                }
            }
            TeamService teamService = factory.getTeamService();
            int records = teamService.getRowsNumber();
            List<Team> teams = teamService.findAll(page, LIMIT);
            request.setAttribute("teams", teams);
            request.setAttribute("page", page);
            request.setAttribute("lastPage", records % LIMIT == 0
                    ? (records / LIMIT) : (records / LIMIT + 1));
            return new Forward("WEB-INF/jsp/teams.jsp");
        } catch (ServiceException e) {
            return sendError(500);
        }
    }
}
