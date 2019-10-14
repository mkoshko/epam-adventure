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
    private static final int LIMIT = 10;

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            PlayerService playerService = factory.getPlayerService();
            int page = 1;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            List<Player> players = playerService.findAll(page, LIMIT);
            int total = playerService.getRowsNumber();
            request.setAttribute("lastPage", calculateLastPage(total, LIMIT));
            request.setAttribute("page", page);
            request.setAttribute("players", players);
            return new Forward("WEB-INF/jsp/players.jsp");
        } catch (ServiceException e) {
            return sendError(500);
        } catch (NumberFormatException e1) {
            return sendError(404);
        }
    }

}
