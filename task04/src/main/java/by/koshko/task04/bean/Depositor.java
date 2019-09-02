package by.koshko.task04.bean;

/**
 * Class describes a depositor entity.
 */
public class Depositor {

    /**
     * First name.
     */
    private String firstName;
    /**
     * Middle name.
     */
    private String middleName;
    /**
     * Last name.
     */
    private String lastName;
    /**
     * Passport identification number.
     */
    private String identification;

    /**
     * Returns first name.
     *
     * @return first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param newFirstName to be set to {@link #firstName}.
     */
    public void setFirstName(final String newFirstName) {
        firstName = newFirstName;
    }

    /**
     * Returns middle name.
     *
     * @return middle name.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the middle name.
     *
     * @param newMiddleName to be set to {@link #middleName}.
     */
    public void setMiddleName(final String newMiddleName) {
        middleName = newMiddleName;
    }

    /**
     * Returns last name.
     *
     * @return last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param newLastName name to be set to {@link #lastName}.
     */
    public void setLastName(final String newLastName) {
        lastName = newLastName;
    }

    /**
     * Returns the identification number.
     *
     * @return string that is a identification number.
     */
    public String getIdentification() {
        return identification;
    }

    /**
     * Sets the identification number.
     *
     * @param newIdentification string to be set to {@link #identification}.
     */
    public void setIdentification(final String newIdentification) {
        identification = newIdentification;
    }



    /**
     * Returns string description of the depositor.
     *
     * @return string description of the depositor.
     */
    @Override
    public String toString() {
        return "Depositor{"
                + "firstName='" + firstName + '\''
                + ", middleName='" + middleName + '\''
                + ", lastName='" + lastName + '\''
                + ", identification='" + identification + '\''
                + '}';
    }
}
