package by.koshko.cyberwikia.controller.user;

import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.bean.Game;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.controller.UserCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

public abstract class AbstractTeamCommand extends UserCommand {

    protected Team readParameters(final HttpServletRequest request)
            throws IOException, ServletException {
        String name = request.getParameter("name");
        String overview = request.getParameter("overview");
        long countryId = Long.parseLong(request.getParameter("country"));
        long gameId = Long.parseLong(request.getParameter("game"));
        Part part = request.getPart("teamLogo");
        Team team = new Team();
        team.setName(name);
        team.setOverview(overview);
        team.setCountry(new Country(countryId));
        team.setGame(new Game(gameId));
        team.setRawData(fillRawData(part));
        return team;
    }
}
