package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.service.PlayerService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlayerProfileCommand extends AbstractCommand {
    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            int playerId = 0;
            try {
                playerId = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                return sendError(404);
            }
            PlayerService playerService = factory.getPlayerService();
            Player player = playerService.loadProfile(playerId);
            if (player == null) {
                return sendError(404);
            }
            request.setAttribute("player", player);
            return new Forward("WEB-INF/jsp/player.jsp");
        } catch (ServiceException e) {
            return sendError(500);
        }
    }
}
