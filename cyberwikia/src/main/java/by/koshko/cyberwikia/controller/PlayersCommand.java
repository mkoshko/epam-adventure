package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.service.PlayerService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PlayersCommand implements Command {

    private Logger logger = LogManager.getLogger(PlayersCommand.class);

    @Override
    public void execute(final HttpServletRequest request, final HttpServletResponse response) {
        try {
//            int page = Integer.parseInt(request.getParameter("page"));
//            int pageLimit = Integer.parseInt(request.getParameter("limit"));
            PlayerService playerService = ServiceFactory.getPlayerService();
//            List<Player> players = playerService.findAll(page * pageLimit, pageLimit);
            List<Player> players = playerService.findAll();
            logger.debug(players);
            request.setAttribute("players", players);
            request.getRequestDispatcher("WEB-INF/jsp/players.jsp").forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            try {
                logger.error(e.getMessage());
                response.sendRedirect("index.jsp");
            } catch (IOException e1) {
                throw new RuntimeException();
            }
        }
    }

}
