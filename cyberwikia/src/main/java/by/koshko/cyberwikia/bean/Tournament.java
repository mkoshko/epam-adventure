package by.koshko.cyberwikia.bean;

import java.time.LocalDate;

/**
 * Tournament entity class.
 */
public class Tournament extends Entity {
    /**
     * Name of the tournament.
     */
    private String name;
    /**
     * Tier of the tournament.
     */
    private String tier;
    /**
     * Prize pool of the tournament.
     */
    private int prize;
    /**
     * Text overview of the tournament.
     */
    private String overview;
    /**
     * Start date of the tournament.
     */
    private LocalDate startDate;
    /**
     * End date of the tournament.
     */
    private LocalDate endDate;

    /**
     * Returns the name of the tournament.
     *
     * @return the name of the tournament.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the tournament.
     *
     * @param tournamentName Name to be set to {@link #name}.
     */
    public void setName(final String tournamentName) {
        name = tournamentName;
    }

    /**
     * Returns the tier of the tournament.
     *
     * @return the tier of the tournament.
     */
    public String getTier() {
        return tier;
    }

    /**
     * Sets the tier of the tournament.
     *
     * @param tournamentTier Tier to be set to {@link #tier}.
     */
    public void setTier(final String tournamentTier) {
        tier = tournamentTier;
    }

    /**
     * Returns the prize pool of the tournament.
     *
     * @return the prize pool of the tournament.
     */
    public int getPrize() {
        return prize;
    }

    /**
     * Sets the prize pool of the tournament.
     *
     * @param tournamentPrize Number to be set to {@link #prize}.
     */
    public void setPrize(final int tournamentPrize) {
        prize = tournamentPrize;
    }

    /**
     * Returns the text overview of the tournament.
     *
     * @return the text overview of the tournament.
     */
    public String getOverview() {
        return overview;
    }

    /**
     * Sets the text overview of the tournament.
     *
     * @param tournamentOverview Text to be set to {@link #overview}.
     */
    public void setOverview(final String tournamentOverview) {
        overview = tournamentOverview;
    }

    /**
     * Returns the date when the tournament begins.
     *
     * @return the date when the tournament begins.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the date when the tournament begins.
     *
     * @param tournamentStartDate Date to be set to {@link #startDate}.
     */
    public void setStartDate(final LocalDate tournamentStartDate) {
        startDate = tournamentStartDate;
    }

    /**
     * Returns the date when the tournament ends.
     *
     * @return the date when the tournament ends.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * The the date when the tournament ends.
     *
     * @param tournamentEndDate Date to be set to {@link #endDate}.
     */
    public void setEndDate(final LocalDate tournamentEndDate) {
        endDate = tournamentEndDate;
    }
}
