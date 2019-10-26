package by.koshko.cyberwikia.controller.moderator;

import by.koshko.cyberwikia.controller.ModeratorCommand;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TournamentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTournamentCommand extends ModeratorCommand {

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            long tournamentId = Long.parseLong(request.getParameter("id"));
            TournamentService tournamentService
                    = factory.getTournamentService();
            tournamentService.deleteTournament(tournamentId);
            return new Forward("tournaments.html");
        } catch (NumberFormatException e1) {
            getLogger().error("Invalid tournament id format.");
            return sendError(400);
        } catch (ServiceException e) {
            getLogger().error(e.getMessage());
            return sendError(500);
        }
    }
}
