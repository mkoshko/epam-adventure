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
            int total = tournamentService.getRowsNumber();
            int page = Pagination.makePagination(request, total);
            if (page > 0) {
                List<Tournament> tournaments = tournamentService.findAll(page, LIMIT);
                request.setAttribute("tournaments", tournaments);
                return new Forward("WEB-INF/jsp/tournaments.jsp");
            } else {
                return sendError(404);
            }
        } catch (ServiceException e1) {
            return sendError(500);
        }
    }
}
