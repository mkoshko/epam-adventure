package by.koshko.task01.entity;

import java.util.Objects;

public class BasicPlan extends PrepaidPlan {

    /**
     * Amount of required argument for object creation.
     */
    public static final int REQUIRED_ARGUMENTS = 10;
    /**
     * Type of a plan.
     */
    public static final PlanType TYPE = PlanType.BASIC;
    /**
     * Amount of free minutes to favourite numbers.
     */
    private int minutesFavouriteNumber;
    /**
     * Amount of free sms to favourite numbers.
     */
    private int smsToFavouriteNumber;

    /**
     * Constructor witch obtains values from {@code Builder} object.
     *
     * @param builder {@code Builder} object.
     */
    public BasicPlan(final BasicPlanBuilder builder) {
        super(builder);
        minutesFavouriteNumber = builder.minutesFavouriteNumber;
        smsToFavouriteNumber = builder.smsToFavouriteNumber;
    }

    public static class BasicPlanBuilder extends PrepaidPlanBuilder {
        /**
         * Amount of free minutes to favourite numbers.
         */
        private int minutesFavouriteNumber;
        /**
         * Amount of free sms to favourite numbers.
         */
        private int smsToFavouriteNumber;

        /**
         * Default constructor.
         */
        public BasicPlanBuilder() {
        }

        /**
         * Sets the internal {@link #minutesFavouriteNumber} value.
         *
         * @param value value to be set to {@link #minutesFavouriteNumber}.
         * @return {@code BasicPlanBuilder} object.
         */
        public BasicPlanBuilder minutesFavouriteNumber(final int value) {
            minutesFavouriteNumber = value;
            return this;
        }

        /**
         * Sets the internal {@link #smsToFavouriteNumber} value.
         *
         * @param value value to be set to {@link #smsToFavouriteNumber}.
         * @return {@code BasicPlanBuilder} object.
         */
        public BasicPlanBuilder smsFavouriteNumber(final int value) {
            smsToFavouriteNumber = value;
            return this;
        }

        /**
         * Invokes creation of {@code BasicPlan} object.
         *
         * @return {@code BasicPlan} instance.
         */
        public BasicPlan build() {
            return new BasicPlan(this);
        }
    }

    /**
     * Returns the amount of minutes to favourite numbers.
     *
     * @return the amount of minutes to favourite numbers.
     */
    public int getMinutesToFavouriteNumber() {
        return minutesFavouriteNumber;
    }

    /**
     * Sets the {@link #minutesFavouriteNumber} value
     * if given value greater or equals to 0.
     *
     * @param value Value to be set to
     *              {@link #minutesFavouriteNumber} value.
     */
    public void setMinutesFavouriteNumber(final int value) {
        minutesFavouriteNumber = value >= 0 ? value : minutesFavouriteNumber;
    }

    /**
     * Returns the amount of free sms to favourite numbers.
     *
     * @return the amount of free sms to favourite numbers.
     */
    public int getSmsToFavouriteNumber() {
        return smsToFavouriteNumber;
    }

    /**
     * Sets the {@link #smsToFavouriteNumber} value
     * if given value greater or equals to 0.
     *
     * @param value Value to be set to
     *              {@link #smsToFavouriteNumber} value.
     */
    public void setSmsToFavouriteNumber(final int value) {
        smsToFavouriteNumber = value >= 0 ? value : smsToFavouriteNumber;
    }

    /**
     * Compares this {@code BasicPlan} to the specified object.
     * Returns {@code true} if and only if the object is not {@code null},
     * and all fields in {@code BasicPlan} object are equals.
     *
     * @param o The object to compare this {@code BasicPlan} against.
     * @return {@code true} if objects are equals.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BasicPlan)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final BasicPlan that = (BasicPlan) o;
        return minutesFavouriteNumber == that.minutesFavouriteNumber
                && getSmsToFavouriteNumber() == that.getSmsToFavouriteNumber();
    }

    /**
     * Returns a hash code for this object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),
                minutesFavouriteNumber,
                getSmsToFavouriteNumber());
    }

    /**
     * Returns the string contains plan description with all parameters.
     *
     * @return The string contains plan description with all parameters.
     */
    @Override
    public String toString() {
        return "BasicPlan{"
                + "plan id =" + getId()
                + ", plan name='" + getPlanName() + '\''
                + ", outgoing within network cost=" + getOutgoingWithinNetwork()
                + ", outgoing other networks cost=" + getOutgoingOtherNetwork()
                + ", outgoing abroad=" + getOutgoingAbroad()
                + ", sms cost=" + getSmsCost()
                + ", megabyte cost=" + getMegabyteCost()
                + ", minutes to favourite numbers=" + getMinutesToFavouriteNumber()
                + ", sms to favourite numbers=" + getSmsToFavouriteNumber()
                + '}';
    }
}
