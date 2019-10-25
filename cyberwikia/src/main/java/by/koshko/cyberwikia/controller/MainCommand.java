package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TeamService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainCommand extends AbstractCommand {
    @Override
    public Forward execute(final HttpServletRequest request,
                        final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            TeamService teamService = factory.getTeamService();
            request.setAttribute("teams", teamService.getTopTeams(5));
            return new Forward("WEB-INF/jsp/index.jsp");
        } catch (ServiceException e) {
            return sendError(500);
        }
    }
}
