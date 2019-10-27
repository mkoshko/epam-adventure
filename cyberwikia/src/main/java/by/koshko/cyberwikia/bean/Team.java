package by.koshko.cyberwikia.bean;

import java.util.List;
import java.util.Objects;

/**
 * Team entity class.
 */
public final class Team extends Entity {
    private static final long serialVersionUID = 1965499834600028453L;
    /**
     * Team name.
     */
    private String name;
    /**
     * Team country id.
     */
    private Country country;
    /**
     * Creator of the team.
     */
    private User creator;
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
    private Game game;
    /**
     * Information about the team.
     */
    private String overview;
    /**
     * Path to team logo image file.
     */
    private String logoFile;

    private transient RawData rawData;

    /**
     * List of tournaments in which the team participated.
     */
    private List<TournamentTeam> tournaments;

    private List<PlayerTeam> players;

    public Team() {
    }

    public Team(final long teamId) {
        super(teamId);
    }

    public RawData getRawData() {
        return rawData;
    }

    public void setRawData(final RawData newRawData) {
        rawData = newRawData;
    }

    public List<PlayerTeam> getPlayers() {
        return players;
    }

    public void setPlayers(final List<PlayerTeam> playerList) {
        players = playerList;
    }

    /**
     * Returns the list of the tournaments.
     *
     * @return the list of the tournaments.
     */
    public List<TournamentTeam> getTournaments() {
        return tournaments;
    }

    /**
     * Sets the list of the tournaments.
     *
     * @param teamTournaments list of tournaments to be set to
     *                        {@link #tournaments}.
     */
    public void setTournaments(final List<TournamentTeam> teamTournaments) {
        tournaments = teamTournaments;
    }

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
    public Country getCountry() {
        return country;
    }

    /**
     * Sets the team country.
     *
     * @param teamCountry Country to be set to {@link #country}.
     */
    public void setCountry(final Country teamCountry) {
        country = teamCountry;
    }

    /**
     * Returns team creator name.
     *
     * @return team creator name.
     */
    public User getCreator() {
        return creator;
    }

    /**
     * Sets the creator of the team.
     *
     * @param teamCreator The name to be set to {@link #creator}.
     */
    public void setCreator(final User teamCreator) {
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
     * Returns the game.
     *
     * @return the game.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Sets the game.
     *
     * @param teamGame Game to be set to {@link #game}.
     */
    public void setGame(final Game teamGame) {
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Team team = (Team) o;
        return Objects.equals(getId(), team.getId())
               && country.equals(team.country)
               && game.equals(team.game)
               && name.equals(team.name)
               && Objects.equals(creator, team.creator)
               && Objects.equals(captain, team.captain)
               && Objects.equals(coach, team.coach)
               && Objects.equals(overview, team.overview)
               && Objects.equals(logoFile, team.logoFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, creator, captain, coach, game,
                overview, logoFile, getId());
    }
}
