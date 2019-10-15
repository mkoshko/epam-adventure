package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.service.PlayerService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PlayersCommand extends AbstractCommand {

    private Logger logger = LogManager.getLogger(PlayersCommand.class);

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            PlayerService playerService = factory.getPlayerService();
            int total = playerService.getRowsNumber();
            int page = Pagination.makePagination(request, total);
            if (page > 0) {
                List<Player> players = playerService.findAll(page, Pagination.getLimit());
                request.setAttribute("players", players);
                return new Forward("WEB-INF/jsp/players.jsp");
            } else {
                return sendError(404);
            }
        } catch (ServiceException e) {
            return sendError(500);
        }
    }

}
