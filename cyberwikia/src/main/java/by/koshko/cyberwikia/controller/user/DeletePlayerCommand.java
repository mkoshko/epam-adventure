package by.koshko.cyberwikia.controller.user;

import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.controller.UserCommand;
import by.koshko.cyberwikia.service.PlayerService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeletePlayerCommand extends UserCommand {

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            HttpSession session = request.getSession(false);
            if (session == null) {
                return new Forward("index.html");
            }
            User user = (User) session.getAttribute("user");
            if (user == null) {
                return sendError(401);
            }
            PlayerService playerService = factory.getPlayerService();
            playerService.deletePlayer(user.getId());
            return sendBack(request);
        } catch (ServiceException e) {
            return sendError(500);
        }
    }
}
