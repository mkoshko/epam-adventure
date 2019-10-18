package by.koshko.cyberwikia.controller.user;

import by.koshko.cyberwikia.bean.ServiceResponse;
import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.controller.UserCommand;
import by.koshko.cyberwikia.service.PlayerTeamService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public final class KickPlayerCommand extends UserCommand {

    private static final Logger LOGGER
            = LogManager.getLogger(KickPlayerCommand.class);

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return sendError(401);
        }
        User user = (User) session.getAttribute("user");
        try (ServiceFactory factory = new ServiceFactory()) {
            long playerId = Long.parseLong(request.getParameter("id"));
            PlayerTeamService playerTeamService = factory.getPlayerTeamService();
            ServiceResponse serviceResponse
                    = playerTeamService.kickPlayer(user.getId(), playerId);
            if (serviceResponse.hasErrors()) {
                setErrors(session, serviceResponse);
                return sendBack(request);
            } else {
                return sendBack(request);
            }
        } catch (NumberFormatException e) {
            return sendError(422);
        } catch (ServiceException e) {
            return sendError(500);
        }
    }
}
