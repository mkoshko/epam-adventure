package by.koshko.cyberwikia.controller.moderator;

import by.koshko.cyberwikia.bean.Tournament;
import by.koshko.cyberwikia.controller.ModeratorCommand;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TournamentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditTournamentPageCommand extends ModeratorCommand {

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            long tournamentId = Integer.parseInt(request.getParameter("id"));
            TournamentService tournamentService = factory.getTournamentService();
            Tournament tournament = tournamentService.getTournamentById(tournamentId);
            if (tournament == null) {
                return sendError(404);
            }
            request.setAttribute("tournament", tournament);
            setScript(request, "js/tournament-form-validation.js");
            return new Forward("WEB-INF/jsp/edittournament.jsp");
        } catch (NumberFormatException e0) {
            return sendError(404);
        } catch (ServiceException e) {
            return sendError(500);
        }
    }
}
