package by.koshko.cyberwikia.controller.moderator;

import by.koshko.cyberwikia.controller.ModeratorCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateTournamentPageCommand extends ModeratorCommand {

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        return new Forward("WEB-INF/jsp/createtournament.jsp");
    }
}
