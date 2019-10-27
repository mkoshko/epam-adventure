package by.koshko.cyberwikia.controller.moderator;

import by.koshko.cyberwikia.bean.ServiceResponse;
import by.koshko.cyberwikia.controller.ModeratorCommand;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TournamentTeamService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddParticipantCommand extends ModeratorCommand {

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            long tournamentId
                    = Long.parseLong(request.getParameter("tournament"));
            String teamName = request.getParameter("teamName");
            TournamentTeamService tournamentTeamService
                    = factory.getTournamentTeamService();
            ServiceResponse resp = tournamentTeamService.
                    addTournamentTeam(tournamentId, teamName);
            if (!resp.hasErrors()) {
                return sendBack(request);
            } else {
                setErrors(request.getSession(), resp);
                return sendBack(request);
            }
        } catch (NumberFormatException e) {
            getLogger().error(e.getMessage());
            return sendError(400);
        } catch (ServiceException e) {
            getLogger().error(e.getMessage());
            return sendError(500);
        }
    }
}
