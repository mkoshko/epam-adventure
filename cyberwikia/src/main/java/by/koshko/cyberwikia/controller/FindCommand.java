package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindCommand extends AbstractCommand {

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        String searchType = request.getParameter("search");
        getLogger().debug("Search type: {}", searchType);
        String searchString = request.getParameter("searchContent");
        getLogger().debug("Search string: {}", searchString);
        try (ServiceFactory factory = new ServiceFactory()) {
            switch (searchType) {
                case "players":
                    request.setAttribute("players", factory.getPlayerService()
                            .findPlayersByNickname(searchString));
                    break;
                case "teams":
                    request.setAttribute("teams", factory.getTeamService()
                            .findTeamsByName(searchString));
                    break;
                default:
                    break;
            }
            request.setAttribute("searchString", searchString);
            return new Forward("WEB-INF/jsp/searchresult.jsp");
        } catch (ServiceException e) {
            return sendError(500);
        }
    }
}
