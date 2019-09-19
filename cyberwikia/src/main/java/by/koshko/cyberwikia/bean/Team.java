package by.koshko.cyberwikia.bean;

import java.util.Objects;

/**
 * Team entity class.
 */
public final class Team extends Entity {
    /**
     * Team name.
     */
    private String name;
    /**
     * Team country id.
     */
    private long countryID;
    /**
     * Creator of the team.
     */
    private Player creator;
    /**
     * Team captain.
     */
    private Player captain;
    /**
     * Team coach.
     */
    private Player coach;
    /**
     * Game that team plays.
     */
    private long gameID;
    /**
     * Information about the team.
     */
    private String overview;
    /**
     * Path to team logo image file.
     */
    private String logoFile;

    /**
     * Returns team name.
     *
     * @return team name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the team name.
     *
     * @param teamName Name to be set to {@link #name}.
     */
    public void setName(final String teamName) {
        name = teamName;
    }

    /**
     * Returns the team countryID.
     *
     * @return the team countryID.
     */
    public long getCountryID() {
        return countryID;
    }

    /**
     * Sets the team countryID.
     *
     * @param teamLocation Country name to be set to {@link #countryID}.
     */
    public void setCountryID(final long teamLocation) {
        countryID = teamLocation;
    }

    /**
     * Returns team creator name.
     *
     * @return team creator name.
     */
    public Player getCreator() {
        return creator;
    }

    /**
     * Sets the creator of the team.
     *
     * @param teamCreator The name to be set to {@link #creator}.
     */
    public void setCreator(final Player teamCreator) {
        creator = teamCreator;
    }

    /**
     * Returns team captain.
     *
     * @return team captain.
     */
    public Player getCaptain() {
        return captain;
    }

    /**
     * Sets the team captain.
     *
     * @param teamCaptain Captain name to be set to {@link #captain}.
     */
    public void setCaptain(final Player teamCaptain) {
        captain = teamCaptain;
    }

    /**
     * Returns team coach.
     *
     * @return team coach.
     */
    public Player getCoach() {
        return coach;
    }

    /**
     * Sets the team coach.
     *
     * @param teamCoach Name to be set to {@link #coach}.
     */
    public void setCoach(final Player teamCoach) {
        coach = teamCoach;
    }

    /**
     * Returns the name of the game.
     *
     * @return the name of the game.
     */
    public long getGameID() {
        return gameID;
    }

    /**
     * Sets the name of the game.
     *
     * @param teamGame Name to be set to {@link #gameID}.
     */
    public void setGameID(final long teamGame) {
        gameID = teamGame;
    }

    /**
     * Returns the text overview of the team.
     *
     * @return the text overview of the team.
     */
    public String getOverview() {
        return overview;
    }

    /**
     * Sets the text overview of the team.
     *
     * @param teamOverview Text to be set to {@link #overview}.
     */
    public void setOverview(final String teamOverview) {
        overview = teamOverview;
    }

    /**
     * Returns the path to the logo image.
     *
     * @return the path to the logo image.
     */
    public String getLogoFile() {
        return logoFile;
    }

    /**
     * Sets the path to the logo image.
     *
     * @param pathToLogoImage Path to logo image to be set to {@link #logoFile}.
     */
    public void setLogoFile(final String pathToLogoImage) {
        logoFile = pathToLogoImage;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(getId(), team.getId())
               && countryID == team.countryID
               && gameID == team.gameID
               && name.equals(team.name)
               && Objects.equals(creator, team.creator)
               && Objects.equals(captain, team.captain)
               && Objects.equals(coach, team.coach)
               && Objects.equals(overview, team.overview)
               && Objects.equals(logoFile, team.logoFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, countryID, creator, captain, coach, gameID,
                overview, logoFile, getId());
    }

    @Override
    public String toString() {
        return "Team{"
               + "name='" + name + '\''
               + ", countryID='" + countryID + '\''
               + ", creator='" + creator + '\''
               + ", captain='" + captain + '\''
               + ", coach='" + coach + '\''
               + ", game='" + gameID + '\''
               + ", overview='" + overview + '\''
               + ", logoFile='" + logoFile + '\''
               + '}';
    }
}
