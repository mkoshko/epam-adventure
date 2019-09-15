package by.koshko.cyberwikia.bean;

/**
 * Team entity class.
 */
public class Team extends Entity {
    /**
     * Team name.
     */
    private String name;
    /**
     * Team location.
     */
    private String location;
    /**
     * Creator of the team.
     */
    private String creator;
    /**
     * Team captain.
     */
    private String captain;
    /**
     * Team coach.
     */
    private String coach;
    /**
     * Game that team plays.
     */
    private String game;
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
     * Returns the team location.
     *
     * @return the team location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the team location.
     *
     * @param teamLocation Country name to be set to {@link #location}.
     */
    public void setLocation(final String teamLocation) {
        location = teamLocation;
    }

    /**
     * Returns team creator name.
     *
     * @return team creator name.
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Sets the creator of the team.
     *
     * @param teamCreator The name to be set to {@link #creator}.
     */
    public void setCreator(final String teamCreator) {
        creator = teamCreator;
    }

    /**
     * Returns team captain.
     *
     * @return team captain.
     */
    public String getCaptain() {
        return captain;
    }

    /**
     * Sets the team captain.
     *
     * @param teamCaptain Captain name to be set to {@link #captain}.
     */
    public void setCaptain(final String teamCaptain) {
        captain = teamCaptain;
    }

    /**
     * Returns team coach.
     *
     * @return team coach.
     */
    public String getCoach() {
        return coach;
    }

    /**
     * Sets the team coach.
     *
     * @param teamCoach Name to be set to {@link #coach}.
     */
    public void setCoach(final String teamCoach) {
        coach = teamCoach;
    }

    /**
     * Returns the name of the game.
     *
     * @return the name of the game.
     */
    public String getGame() {
        return game;
    }

    /**
     * Sets the name of the game.
     *
     * @param teamGame Name to be set to {@link #game}.
     */
    public void setGame(final String teamGame) {
        game = teamGame;
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
}
