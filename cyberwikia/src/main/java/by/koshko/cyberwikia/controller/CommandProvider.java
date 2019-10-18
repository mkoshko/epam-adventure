package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.controller.moderator.CreateTournamentCommand;
import by.koshko.cyberwikia.controller.moderator.CreateTournamentPageCommand;
import by.koshko.cyberwikia.controller.moderator.EditTournamentPageCommand;
import by.koshko.cyberwikia.controller.moderator.SaveTournamentCommand;
import by.koshko.cyberwikia.controller.user.CreatePlayerCommand;
import by.koshko.cyberwikia.controller.user.CreatePlayerPageCommand;
import by.koshko.cyberwikia.controller.user.DeletePlayerCommand;
import by.koshko.cyberwikia.controller.user.JoinTeamCommand;
import by.koshko.cyberwikia.controller.user.KickPlayerCommand;
import by.koshko.cyberwikia.controller.user.LeaveTeamCommand;
import by.koshko.cyberwikia.controller.user.PlayerEditCommand;
import by.koshko.cyberwikia.controller.user.PlayerEditPageCommand;
import by.koshko.cyberwikia.controller.user.ProfilesPageCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static final Map<String, AbstractCommand> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("/index", new MainCommand());
        COMMANDS.put("/players", new PlayersCommand());
        COMMANDS.put("/teams", new TeamsCommand());
        COMMANDS.put("/team", new TeamCommand());
        COMMANDS.put("/join", new JoinTeamCommand());
        COMMANDS.put("/leave", new LeaveTeamCommand());
        COMMANDS.put("/signin", new SignInCommand());
        COMMANDS.put("/signout", new SignOutCommand());
        COMMANDS.put("/lang", new LanguageCommand());
        COMMANDS.put("/player", new PlayerProfileCommand());
        COMMANDS.put("/editplayer", new PlayerEditPageCommand());
        COMMANDS.put("/saveplayer", new PlayerEditCommand());
        COMMANDS.put("/registration", new RegistrationPageCommand());
        COMMANDS.put("/signup", new SignUpCommand());
        COMMANDS.put("/mypages", new ProfilesPageCommand());
        COMMANDS.put("/tournament", new TournamentCommand());
        COMMANDS.put("/tournaments", new TournamentsCommand());
        COMMANDS.put("/deleteplayer", new DeletePlayerCommand());
        COMMANDS.put("/createplayerform", new CreatePlayerPageCommand());
        COMMANDS.put("/createplayer", new CreatePlayerCommand());
        COMMANDS.put("/kickplayer", new KickPlayerCommand());
        COMMANDS.put("/createtournamentform", new CreateTournamentPageCommand());
        COMMANDS.put("/createtournament", new CreateTournamentCommand());
        COMMANDS.put("/edittournament", new EditTournamentPageCommand());
        COMMANDS.put("/savetournament", new SaveTournamentCommand());
    }

    public static AbstractCommand getCommand(final String action) {
        return COMMANDS.get(action);
    }
}
