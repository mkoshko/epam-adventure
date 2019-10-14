package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Tournament;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.TournamentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TournamentsCommand extends AbstractCommand {

    private Logger logger = LogManager.getLogger(TournamentsCommand.class);
    private static final int LIMIT = 10;

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        try (ServiceFactory factory = new ServiceFactory()) {
            TournamentService tournamentService
                    = factory.getTournamentService();
            int page = 1;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            List<Tournament> tournaments = tournamentService.findAll(page, LIMIT);
            int total = tournamentService.getRowsNumber();
            request.setAttribute("tournaments", tournaments);
            request.setAttribute("page", page);
            request.setAttribute("lastPage", calculateLastPage(total, LIMIT));
            return new Forward("WEB-INF/jsp/tournaments.jsp");
        } catch (NumberFormatException e0) {
            return sendError(404);
        } catch (ServiceException e1) {
            logger.error("Cannot handle the request. {}", e1.getMessage());
            return sendError(500);
        }
    }
}
