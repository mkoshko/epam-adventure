package by.koshko.cyberwikia.controller.moderator;

import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.bean.Tournament;
import by.koshko.cyberwikia.bean.TournamentTeam;
import by.koshko.cyberwikia.controller.ModeratorCommand;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TournamentTeamService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveParticipantCommand extends ModeratorCommand {

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            TournamentTeamService service = factory.getTournamentTeamService();
            long tournamentId
                    = Long.parseLong(request.getParameter("tournament"));
            long teamId = Long.parseLong(request.getParameter("team"));
            TournamentTeam entity = new TournamentTeam();
            entity.setTeam(new Team(teamId));
            entity.setTournament(new Tournament(tournamentId));
            service.deleteTournamentTeam(entity);
            return sendBack(request);
        } catch (NumberFormatException e1) {
            getLogger().error(e1.getMessage());
            return sendError(400);
        } catch (ServiceException e) {
            getLogger().error(e.getMessage());
            return sendError(500);
        }
    }
}
