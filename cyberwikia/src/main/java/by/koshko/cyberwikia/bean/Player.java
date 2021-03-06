package by.koshko.cyberwikia.bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Player entity class.
 */
public final class Player extends Entity {

    private static final long serialVersionUID = 1948680934600028883L;
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
     * Players country.
     */
    private Country country;
    /**
     * Text overview.
     */
    private String overview;

    private transient RawData rawData;

    private List<PlayerTeam> playerTeams;

    private List<TournamentTeam> tournaments;

    public Player() {
    }

    public Player(final long id) {
        super(id);
    }

    private Player(final Builder builder) {
        super(builder.id);
        nickname = builder.nickname;
        firstName = builder.firstName;
        lastName = builder.lastName;
        birth = builder.birth;
        country = builder.country;
        profilePhoto = builder.profilePhoto;
        overview = builder.overview;
    }

    public static class Builder {
        //Required parameters.
        private String nickname;
        private String firstName;
        private String lastName;
        private LocalDate birth;
        private Country country;
        //Optional parameters.
        private long id;
        private String profilePhoto;
        private String overview;

        public Builder(
                             final String newNickname,
                             final String newFirstName,
                             final String newLastName,
                             final LocalDate newBirth,
                             final Country newCountry) {
            nickname = newNickname;
            firstName = newFirstName;
            lastName = newLastName;
            birth = newBirth;
            country = newCountry;
        }

        public Builder setId(final long newId) {
            id = newId;
            return this;
        }

        public Builder setProfilePhoto(final String newProfilePhoto) {
            profilePhoto = newProfilePhoto;
            return this;
        }

        public Builder setOverview(final String newOverview) {
            overview = newOverview;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }

    public List<TournamentTeam> getTournaments() {
        return tournaments;
    }

    public void setTournaments(final List<TournamentTeam> tournamentList) {
        tournaments = tournamentList;
    }

    public List<PlayerTeam> getPlayerTeams() {
        return playerTeams;
    }

    public void setPlayerTeams(final List<PlayerTeam> newPlayerTeams) {
        playerTeams = newPlayerTeams;
    }

    public RawData getRawData() {
        return rawData;
    }

    public void setRawData(final RawData newRawData) {
        rawData = newRawData;
    }

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
     * Returns the country of the player.
     *
     * @return the country of the player.
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Sets the country of the player.
     *
     * @param playerCountry Country id to be set to {@link #country}.
     */
    public void setCountry(final Country playerCountry) {
        country = playerCountry;
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
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(getId(), player.getId())
               && country.equals(player.country)
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
                country, overview, getId());
    }
}
