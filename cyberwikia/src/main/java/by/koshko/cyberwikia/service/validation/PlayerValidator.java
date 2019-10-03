package by.koshko.cyberwikia.service.validation;

import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Map;

public class PlayerValidator implements Validator {

    private Logger logger = LogManager.getLogger(PlayerValidator.class);

    private static final String NICKNAME_MAX_LENGTH = "player.nickname";
    private static final String FIRSTNAME_MAX_LENGTH = "player.firstName";
    private static final String LASTNAME_MAX_LENGTH = "player.lastName";
    private static final String OVERVIEV_MAX_LENGTH = "player.overview";

    private int nicknameMaxLength;
    private int firstNameMaxLength;
    private int lastNameMaxLength;
    private int overviewMaxLength;

    public void init() throws ServiceException {
        Map<String, String> values = ValidationPropertiesLoader.getValues();
        nicknameMaxLength = Integer.parseInt(values.get(NICKNAME_MAX_LENGTH));
        firstNameMaxLength = Integer.parseInt(values.get(FIRSTNAME_MAX_LENGTH));
        lastNameMaxLength = Integer.parseInt(values.get(LASTNAME_MAX_LENGTH));
        overviewMaxLength = Integer.parseInt(values.get(OVERVIEV_MAX_LENGTH));
    }

    public boolean test(final Player player, final boolean checkID) {
        if (player == null) {
            return false;
        }
        String nickname = player.getNickname();
        String firstName = player.getFirstName();
        String lastName = player.getLastName();
        LocalDate birth = player.getBirth();
        Country country = player.getCountry();
        String overview = player.getOverview();
        if (checkID && (player.getId() <= 0)) {
            logger.debug("Invalid player ID.");
            return false;
        }
        if (nickname == null
            || nickname.isBlank()
            || nickname.length() > nicknameMaxLength) {
            logger.debug("Invalid player nickname.");
            return false;
        }
        if (firstName == null
            || firstName.isBlank()
            || firstName.length() > firstNameMaxLength) {
            logger.debug("Invalid player first name.");
            return false;
        }
        if (lastName == null
            || lastName.isBlank()
            || lastName.length() > lastNameMaxLength) {
            logger.debug("Invalid player last name.");
            return false;
        }
        if (birth == null) {
            logger.debug("Invalid player birth date.");
            return false;
        }
        if (country == null || country.getId() <= 0) {
            logger.debug("Invalid player country.");
            return false;
        }
        if (overview != null && overview.length() > overviewMaxLength) {
            logger.debug("Invalid length of players overview text.");
            return false;
        }
        return true;
    }
}
