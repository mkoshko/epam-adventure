package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Role;
import by.koshko.cyberwikia.bean.Tournament;
import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TournamentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TournamentCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(TournamentCommand.class);

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            TournamentService tournamentService = factory.getTournamentService();
            long id = Long.parseLong(request.getParameter("id"));
            Tournament tournament = tournamentService.getTournamentById(id);
            if (tournament == null) {
                return sendError(404);
            }
            request.setAttribute("tournament", tournament);
            setScriptForModerator(request);
            return new Forward("WEB-INF/jsp/tournament.jsp");
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            return sendError(500);
        } catch (NumberFormatException e1) {
            return sendError(404);
        }
    }

    private void setScriptForModerator(final HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        User user = (User) session.getAttribute("user");
        if (user != null && user.getRole() == Role.EVENT_MODERATOR) {
            setScript(request, "js/placement.js");
        }
    }
}
