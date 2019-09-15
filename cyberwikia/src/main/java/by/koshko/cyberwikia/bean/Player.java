package by.koshko.cyberwikia.bean;

import java.time.LocalDate;

/**
 * Player entity class.
 */
public class Player extends Entity {
    /**
     * Players nickname.
     */
    private String nickname;
    /**
     * Players first name.
     */
    private String firstName;
    /**
     * Players last name.
     */
    private String lastName;
    /**
     * Players day of birth.
     */
    private LocalDate birth;
    /**
     * Players country.
     */
    private String country;

    /**
     * Returns the nickname of the player.
     *
     * @return the nickname of the player.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets the players {@link #nickname} value.
     *
     * @param playerNickname Value to be set to {@link #nickname}.
     */
    public void setNickname(final String playerNickname) {
        nickname = playerNickname;
    }

    /**
     * Returns players first name.
     *
     * @return players first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the {@link #firstName} value.
     *
     * @param playerFirstName Value to be set to {@link #firstName}.
     */
    public void setFirstName(final String playerFirstName) {
        firstName = playerFirstName;
    }

    /**
     * Returns the {@link #lastName} value.
     *
     * @return players last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the {@link #lastName} value.
     *
     * @param playerLastName Value to be to {@link #lastName}.
     */
    public void setLastName(final String playerLastName) {
        lastName = playerLastName;
    }

    /**
     * Returns players date of birth.
     *
     * @return players date of birth.
     */
    public LocalDate getBirth() {
        return birth;
    }

    /**
     * Sets the date of birth of the player.
     *
     * @param playerBirthday Date to be set to {@link #birth}.
     */
    public void setBirth(final LocalDate playerBirthday) {
        birth = playerBirthday;
    }

    /**
     * Returns the country of the player.
     *
     * @return the country of the player.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of the player.
     *
     * @param playerCountry Country to be set to {@link #country}.
     */
    public void setCountry(final String playerCountry) {
        country = playerCountry;
    }
}