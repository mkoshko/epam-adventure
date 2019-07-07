package by.koshko.task01.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Customer {
    //TODO getLogger()
    /** It's just a logger man, relax. */
    private static Logger logger = LogManager.getLogger("Logger");
    /**
     * Unique customer identifier.
     */
    private long customerId;
    /**
     * Customer first name.
     */
    private String firstName;
    /**
     * Customer last name.
     */
    private String lastName;
    /**
     * Customer middle name.
     */
    private String middleName;
    /**
     * Customer phone number.
     */
    private String phoneNumber;
    /**
     * Customer tariff plan.
     */
    private CommonPlan customerTariffPlan;

    /**
     * Initializes a newly created {@code Customer} object with parameters.
     *
     * @param id              Customers id.
     * @param fName           Customers first name.
     * @param lName           Customers last name.
     * @param mName           Customers middle name.
     * @param pNumber         Customers phone number.
     * @param tariffPlan      Customers tariff plan.
     */
    public Customer(final long id,
                    final String fName,
                    final String lName,
                    final String mName,
                    final String pNumber,
                    final CommonPlan tariffPlan) {
//        if (fName == null || lName == null
//                || mName == null
//                || pNumber == null
//                || tariffPlan == null) {
//            logger.error("Attempt to create object with null parameters");
//            throw new NullPointerException("Not null parameters expected");
//        }
        customerId = id;
        firstName = fName;
        lastName = lName;
        middleName = mName;
        phoneNumber = pNumber;
        customerTariffPlan = tariffPlan;
    }

    /**
     * Customer ID getter.
     *
     * @return customer ID.
     */
    public long getCustomerId() {
        return customerId;
    }

    /**
     * Customer first name getter.
     *
     * @return customer first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Customer last name getter.
     *
     * @return customer last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Customer middle name getter.
     *
     * @return customer middle name.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Customer phone getter.
     *
     * @return customer phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Customer tariff plan getter.
     *
     * @return customer tariff plan.
     */
    public CommonPlan getCustomerTariffPlan() {
        return customerTariffPlan;
    }

    /**
     * Compares this Customer to the specified object.
     * The result is {@code true} if and only if the object
     * is not {@code null} and have the same {@link #customerId}.
     *
     * @param o The object to compare this {@code Customer} against
     * @return {@code true} if the given object have
     * the same {@link #customerId}, {@code false} otherwise.
     */
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Customer obj = (Customer) o;
        return (customerId == obj.getCustomerId())
                && (phoneNumber.equals(obj.getPhoneNumber()));
    }

    /**
     * Returns hash code for this object.
     *
     * @return a hash code value for this object.
     */
    public int hashCode() {
        final int prime = 32;
        int hash = (int) (customerId ^ (customerId >>> prime));
        hash = hash * prime + (firstName != null ? 0 : firstName.hashCode());
        hash = hash * prime + (lastName != null ? 0 : lastName.hashCode());
        hash = hash * prime + (middleName != null ? 0 : middleName.hashCode());
        return hash;
    }
}
