package by.koshko.task04.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents a bank entity.
 */
public class Bank {

    /**
     * Name of the bank.
     */
    private String name;
    /**
     * List of {@link Deposit} objects.
     */
    private List<Deposit> deposits = new ArrayList<>();
    /**
     * Registration country of the bank.
     */
    private String country;
    /**
     * URL address of the bank.
     */
    private String site;
    /**
     * Licence.
     */
    private boolean licence;

    /**
     * Returns name of the bank.
     *
     * @return name of the bank.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the bank.
     *
     * @param bankName to be set to bank name.
     */
    public void setName(final String bankName) {
        if (bankName != null) {
            name = bankName;
        }
    }

    /**
     * Returns list of deposits.
     *
     * @return list of deposits.
     */
    public List<Deposit> getDeposits() {
        return deposits;
    }

    /**
     * Adds deposit to {@link #deposits}.
     *
     * @param newDeposits deposit to be added to {@link #deposits}.
     */
    public void addDeposit(final Deposit newDeposits) {
        deposits.add(newDeposits);
    }

    /**
     * Returns registration country of the bank.
     *
     * @return registration country of the bank.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of the bank.
     *
     * @param registrationCountry country of the bank.
     */
    public void setCountry(final String registrationCountry) {
        if (registrationCountry != null) {
            country = registrationCountry;
        }
    }

    /**
     * Returns site of the bank.
     *
     * @return site of the bank.
     */
    public String getSite() {
        return site;
    }

    /**
     * Sets the site of the bank.
     *
     * @param newSite site of the bank.
     */
    public void setSite(final String newSite) {
        if (newSite != null) {
            site = newSite;
        }
    }

    /**
     * Returns {@code true} if bank has licence, {@code false} otherwise.
     *
     * @return {@code true} if bank has licence, {@code false} otherwise.
     */
    public boolean isLicence() {
        return licence;
    }

    /**
     * Sets the {@link #licence} value.
     *
     * @param newLicence value to be set to {@link #licence}.
     */
    public void setLicence(final boolean newLicence) {
        licence = newLicence;
    }

    /**
     * String representation of a bank.
     *
     * @return string representation of a bank.
     */
    @Override
    public String toString() {
        return "Bank{"
                + "name='" + name + '\''
                + ", deposits=" + deposits
                + ", country='" + country + '\''
                + ", site='" + site + '\''
                + ", licence=" + licence
                + '}';
    }
}
