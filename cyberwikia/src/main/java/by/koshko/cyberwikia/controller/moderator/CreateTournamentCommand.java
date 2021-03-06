package by.koshko.cyberwikia.controller.moderator;

import by.koshko.cyberwikia.bean.EntityError;
import by.koshko.cyberwikia.bean.ServiceResponse;
import by.koshko.cyberwikia.bean.Tournament;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TournamentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.format.DateTimeParseException;

public final class CreateTournamentCommand extends AbstractTournamentCommand {

    private static final Logger LOGGER
            = LogManager.getLogger(CreateTournamentCommand.class);
    private final String createTournamentUrl = "createtournamentform.html";

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try (ServiceFactory factory = new ServiceFactory()) {
            Tournament tournament = readParameters(request);
            TournamentService tournamentService = factory.getTournamentService();
            ServiceResponse serviceResponse
                    = tournamentService.createTournament(tournament);
            if (!serviceResponse.hasErrors()) {
                return new Forward("tournaments.html");
            } else {
                setErrors(session, serviceResponse);
                return new Forward(createTournamentUrl);
            }
        } catch (DateTimeParseException | NumberFormatException e) {
            ServiceResponse serviceResponse = new ServiceResponse();
            serviceResponse.addErrorMessage(EntityError.REQUIRED_NOT_NULL);
            setErrors(session, serviceResponse);
            return new Forward(createTournamentUrl);
        } catch (ServiceException | IOException | ServletException e1) {
            LOGGER.error(e1.getMessage());
            return sendError(500);
        }
    }
}
