package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.service.PlayerService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PlayerEditPageCommand extends UserCommand {

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            long playerId = 0;
            try {
                playerId = Long.parseLong(request.getParameter("id"));
            } catch (NumberFormatException e) {
                return sendError(404);
            }
            PlayerService playerService = factory.getPlayerService();
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            if (user.getId() != playerId) {
                return sendError(404);
            }
            Player player = playerService.findById(playerId);
            if (player == null) {
                return sendError(404);
            }
            request.setAttribute("player", player);
            return new Forward("WEB-INF/jsp/editplayer.jsp");
        } catch (ServiceException e) {
            return sendError(500);
        }
    }
}
