package by.koshko.cyberwikia.controller.user;

import by.koshko.cyberwikia.bean.ServiceResponse;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TeamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.format.DateTimeParseException;

public final class CreateTeamCommand extends AbstractTeamCommand {

    private static final Logger LOGGER
            = LogManager.getLogger(CreateTeamCommand.class);

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try (ServiceFactory factory = new ServiceFactory()) {
            User user  = (User) session.getAttribute("user");
            Team team = readParameters(request);
            TeamService teamService = factory.getTeamService();
            ServiceResponse serviceResponse
                    = teamService.createTeam(user.getId(), team);
            if (!serviceResponse.hasErrors()) {
                return new Forward("mypages.html");
            } else {
                setErrors(session, serviceResponse);
                return sendBack(request);
            }
        } catch (DateTimeParseException | NumberFormatException e0) {
            return sendError(400);
        } catch (ServiceException | IOException | ServletException e) {
            LOGGER.error(e.getMessage());
            return sendError(500);
        }
    }
}
