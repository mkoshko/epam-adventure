package by.koshko.cyberwikia.controller.moderator;

import by.koshko.cyberwikia.bean.Tournament;
import by.koshko.cyberwikia.controller.ModeratorCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.time.LocalDate;

public abstract class AbstractTournamentCommand extends ModeratorCommand {

    protected Tournament readParameters(final HttpServletRequest request)
            throws IOException, ServletException {
        String overview = request.getParameter("overview");
        String name = request.getParameter("name");
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
        int prize = Integer.parseInt(request.getParameter("prize"));
        Part part = request.getPart("tournament-logo");
        Tournament tournament = new Tournament();
        tournament.setName(name);
        tournament.setOverview(overview);
        tournament.setStartDate(startDate);
        tournament.setEndDate(endDate);
        tournament.setRawData(fillRawData(part));
        tournament.setPrize(prize);
        return tournament;
    }
}
