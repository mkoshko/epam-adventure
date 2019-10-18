package by.koshko.cyberwikia.controller.moderator;

import by.koshko.cyberwikia.bean.EntityError;
import by.koshko.cyberwikia.bean.ServiceResponse;
import by.koshko.cyberwikia.bean.Tournament;
import by.koshko.cyberwikia.controller.ModeratorCommand;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TournamentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public final class CreateTournamentCommand extends ModeratorCommand {

    private static final Logger LOGGER
            = LogManager.getLogger(CreateTournamentCommand.class);

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try (ServiceFactory factory = new ServiceFactory()) {
            String name = request.getParameter("name");
            String overview = request.getParameter("overview");
            LocalDate startDate
                    = LocalDate.parse(request.getParameter("startDate"));
            LocalDate endDate
                    = LocalDate.parse(request.getParameter("endDate"));
            int prize = Integer.parseInt(request.getParameter("prize"));
            Part part = request.getPart("tournament-logo");
            Tournament tournament = new Tournament();
            tournament.setName(name);
            tournament.setOverview(overview);
            tournament.setStartDate(startDate);
            tournament.setEndDate(endDate);
            tournament.setPrize(prize);
            tournament.setRawData(fillRawData(part));
            TournamentService tournamentService = factory.getTournamentService();
            ServiceResponse serviceResponse
                    = tournamentService.createTournament(tournament);
            if (!serviceResponse.hasErrors()) {
                return new Forward("tournaments.html");
            } else {
                setErrors(session, serviceResponse);
                return new Forward("createtournamentform.html");
            }
        } catch (DateTimeParseException | NumberFormatException e) {
            ServiceResponse serviceResponse = new ServiceResponse();
            serviceResponse.addErrorMessage(EntityError.REQUIRED_NOT_NULL);
            setErrors(session, serviceResponse);
            return new Forward("createtournamentform.html");
        } catch (ServiceException | IOException | ServletException e1) {
            LOGGER.error(e1.getMessage());
            return sendError(500);
        }
    }
}
