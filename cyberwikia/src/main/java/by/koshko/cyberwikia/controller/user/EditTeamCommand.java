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

public class EditTeamCommand extends AbstractTeamCommand {

    private Logger logger = LogManager.getLogger();

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try (ServiceFactory factory = new ServiceFactory()) {
            User user = (User) session.getAttribute("user");
            Team team = readParameters(request);
            TeamService teamService = factory.getTeamService();
            ServiceResponse serviceResponse
                    = teamService.updateTeam(user.getId(), team);
            return makeForward("mypages.html", "editteam.html",
                    serviceResponse, session);
        } catch (NumberFormatException e) {
            return sendError(400);
        } catch (ServiceException | ServletException | IOException e1) {
            logger.error(e1.getMessage());
            return sendError(500);
        }
    }
}
