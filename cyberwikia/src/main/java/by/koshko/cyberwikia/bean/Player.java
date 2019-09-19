package by.koshko.cyberwikia.bean;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Player entity class.
 */
public final class Player extends Entity {
    /**
     * Players nickname.
     */
    private String nickname;
    /**
     * Path to profile photo file.
     */
    private String profilePhoto;
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
     * Players countryID id.
     */
    private long countryID;
    /**
     * Text overview.
     */
    private String overview;

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
     * Returns the path to the profile photo image.
     *
     * @return the path to the profile photo image.
     */
    public String getProfilePhoto() {
        return profilePhoto;
    }

    /**
     * Sets the path to the profile photo image.
     *
     * @param playerProfilePhoto Path to be set to {@link #profilePhoto}.
     */
    public void setProfilePhoto(final String playerProfilePhoto) {
        this.profilePhoto = playerProfilePhoto;
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
     * Returns the countryID id of the player.
     *
     * @return the countryID id of the player.
     */
    public long getCountryID() {
        return countryID;
    }

    /**
     * Sets the countryID id of the player.
     *
     * @param playerCountry Country id to be set to {@link #countryID}.
     */
    public void setCountryID(final long playerCountry) {
        countryID = playerCountry;
    }

    /**
     * Returns text overview of the player.
     *
     * @return text overview of the player.
     */
    public String getOverview() {
        return overview;
    }

    /**
     * Sets the text overview of the player.
     *
     * @param playerOverview Text to be set to {@link #overview}.
     */
    public void setOverview(final String playerOverview) {
        overview = playerOverview;
    }

    @Override
    public String toString() {
        return "Player{"
                + "nickname='" + nickname + '\''
                + ", profilePhoto='" + profilePhoto + '\''
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", birth=" + birth
                + ", countryID='" + countryID + '\''
                + ", overview='" + overview + '\''
                + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(getId(), player.getId())
               && countryID == player.countryID
               && Objects.equals(nickname, player.nickname)
               && Objects.equals(profilePhoto, player.profilePhoto)
               && Objects.equals(firstName, player.firstName)
               && Objects.equals(lastName, player.lastName)
               && Objects.equals(birth, player.birth)
               && Objects.equals(overview, player.overview);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, profilePhoto, firstName, lastName, birth,
                countryID, overview, getId());
    }
}
