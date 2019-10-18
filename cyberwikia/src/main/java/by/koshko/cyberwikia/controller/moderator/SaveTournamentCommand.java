package by.koshko.cyberwikia.controller.moderator;

import by.koshko.cyberwikia.bean.EntityError;
import by.koshko.cyberwikia.bean.ServiceResponse;
import by.koshko.cyberwikia.bean.Tournament;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TournamentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.format.DateTimeParseException;

public class SaveTournamentCommand extends AbstractTournamentCommand {

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try (ServiceFactory factory = new ServiceFactory()) {
            long tournamentId = Long.parseLong(request.getParameter("id"));
            Tournament tournament = readParameters(request);
            tournament.setId(tournamentId);
            TournamentService tournamentService = factory.getTournamentService();
            ServiceResponse serviceResponse
                    = tournamentService.updateTournament(tournament);
            if (!serviceResponse.hasErrors()) {
                return new Forward("tournament.html?id=" + tournament.getId());
            } else {
                setErrors(session, serviceResponse);
                return sendBack(request);
            }
        } catch (DateTimeParseException | NumberFormatException e) {
            ServiceResponse serviceResponse = new ServiceResponse();
            serviceResponse.addErrorMessage(EntityError.REQUIRED_NOT_NULL);
            setErrors(session, serviceResponse);
            return sendBack(request);
        } catch (ServiceException | IOException | ServletException e) {
            return sendError(500);
        }
    }
}
