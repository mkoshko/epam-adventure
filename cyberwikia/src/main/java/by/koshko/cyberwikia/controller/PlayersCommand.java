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

public class PlayersCommand extends AbstractCommand {

    private Logger logger = LogManager.getLogger(PlayersCommand.class);

    @Override
    public void execute(final HttpServletRequest request, final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            PlayerService playerService = factory.getPlayerService();
            int page = 1;
            if (request.getParameter("page") != null) {
                try {
                    page = Integer.parseInt(request.getParameter("page"));
                } catch (NumberFormatException e) {

                }
            }
            List<Player> players = playerService.findAll(page, 10);
            if (players.size() == 0) {
                page = 1;
            }
            int records = playerService.getRowsNumber();
            request.setAttribute("lastPage", records % 10 == 0 ? records / 10 : records / 10 + 1);
            request.setAttribute("page", page);
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
