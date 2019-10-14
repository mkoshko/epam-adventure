package by.koshko.cyberwikia.service.validation;

import by.koshko.cyberwikia.bean.Tournament;
import by.koshko.cyberwikia.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Map;

public class TournamentValidator implements Validator {

    private Logger logger = LogManager.getLogger(TournamentValidator.class);

    private static final String NAME_MAX_LENGTH = "tournament.name";
    private static final String OVERVIEW_MAX_LENGTH = "tournament.overview";

    private int nameMaxLength;
    private int overviewMaxLength;

    public void init() throws ServiceException {
        Map<String, String> values = ValidationPropertiesLoader.getValues();
        nameMaxLength = Integer.parseInt(values.get(NAME_MAX_LENGTH));
        overviewMaxLength = Integer.parseInt(values.get(OVERVIEW_MAX_LENGTH));
    }

    public boolean test(final Tournament tournament, final boolean checkID) {
        if (tournament == null) {
            return false;
        }
        String name = tournament.getName();
        int prize = tournament.getPrize();
        String overview = tournament.getOverview();
        LocalDate startDate = tournament.getStartDate();
        LocalDate endDate = tournament.getEndDate();

        if (name == null || name.isBlank() || name.length() > nameMaxLength) {
            logger.debug("Invalid tournament name");
            return false;
        }
        if (prize < 0) {
            logger.debug("Negative tournament prize value.");
            return false;
        }
        if (overview != null && overview.length() > overviewMaxLength) {
            logger.debug("Overview length is too large.");
            return false;
        }
        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            logger.debug("Invalid tournament dates.");
            return false;
        }
        if (checkID && tournament.getId() <= 0) {
            logger.debug("Invalid tournament ID");
            return false;
        }
        return true;
    }

}
