package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TeamCommand extends AbstractCommand {
    private Logger logger = LogManager.getLogger(TeamCommand.class);

    //TODO Needs refactoring!
    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            Forward forward = new Forward("WEB-INF/jsp/team.jsp");
            int teamId;
            try {
                teamId = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                return sendError(404);
            }
            TeamService teamService = factory.getTeamService();
            Team team = teamService.loadTeamProfile(teamId);
            if (team == null) {
                return sendError(404);
            }
            request.setAttribute("team", team);
            PlayerTeamService pts = factory.getPlayerTeamService();
            HttpSession session = request.getSession(false);
            if (session == null) {
                return forward;
            }
            User user = (User) session.getAttribute("user");
            if (user == null) {
                return forward;
            }
            PlayerService playerService = factory.getPlayerService();
            Player player = playerService.findById(user.getId());
            if (player == null) {
                request.setAttribute("activeTeamId", -1L);
                return forward;
            }
            long activeTeamId = pts.playerActiveTeamId(player.getId());
            request.setAttribute("activeTeamId", activeTeamId);
            logger.debug("Active team id: {}", activeTeamId);
            return forward;
        } catch (ServiceException e) {
            logger.debug(e.getMessage());
            return sendError(500);
        }
    }
}
