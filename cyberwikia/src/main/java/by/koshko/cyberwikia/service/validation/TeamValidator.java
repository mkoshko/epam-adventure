package by.koshko.cyberwikia.service.validation;

import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.bean.Game;
import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class TeamValidator implements Validator {

    private Logger logger = LogManager.getLogger(TeamValidator.class);

    private static final String NAME_MAX_LENGTH = "team.name";
    private static final String OVERVIEW_MAX_LENGTH = "team.overview";

    private int nameMaxLength;
    private int overviewMaxLength;

    public void init() throws ServiceException {
        Map<String, String> values = ValidationPropertiesLoader.getValues();
        nameMaxLength = Integer.parseInt(values.get(NAME_MAX_LENGTH));
        overviewMaxLength = Integer.parseInt(values.get(OVERVIEW_MAX_LENGTH));
    }

    public boolean test(final Team team, final boolean checkID) {
        if (team == null) {
            return false;
        }
        String name = team.getName();
        String overview = team.getOverview();
        Country country = team.getCountry();
        Game game = team.getGame();
        Player creator = team.getCreator();
        if (name == null
            || name.isBlank()
            || name.length() > nameMaxLength) {
            logger.debug("Invalid team name.");
            return false;
        }
        if (overview != null && overview.length() > overviewMaxLength) {
            logger.debug("Invalid team overview.");
            return false;
        }
        if (country == null || country.getId() <= 0) {
            logger.debug("Invalid teams country ID.");
            return false;
        }
        if (game == null || game.getId() <= 0) {
            logger.debug("Invalid game ID");
            return false;
        }
        if (creator == null || creator.getId() <= 0) {
            logger.debug("Invalid team creator ID");
            return false;
        }
        if (checkID && team.getId() <= 0) {
            logger.debug("Invalid team ID.");
            return false;
        }
        return true;
    }
}
