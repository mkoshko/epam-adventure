package by.koshko.task01.entity;

import java.util.Objects;

public class InternetPlan extends PostpaidPlan {
    /**
     * Amount of required argument for object creation.
     */
    public static final int REQUIRED_ARGUMENTS = 9;
    /**
     * Type of a plan.
     */
    public static final PlanType TYPE = PlanType.INTERNET;
    /**
     * Amount of included internet traffic.
     */
    private int internetTraffic;

    /**
     * Constructor witch obtains values from builder object.
     *
     * @param builder {@code Builder} object.
     */
    public InternetPlan(final InternetPlanBuilder builder) {
        super(builder);
        internetTraffic = builder.internetTraffic;
    }

    public static class InternetPlanBuilder extends PostpaidBuilder {
        /**
         * Amount of included internet traffic.
         */
        private int internetTraffic;

        /**
         * Default constructor.
         */
        public InternetPlanBuilder() {
        }

        /**
         * Sets the internet traffic value.
         *
         * @param value internet traffic value.
         * @return builder object.
         */
        public InternetPlanBuilder internetTraffic(final int value) {
            internetTraffic = value;
            return this;
        }

        /**
         * Invokes creation of {@code InternetPlan} object.
         *
         * @return {@code InternetPlan} instance.
         */
        public InternetPlan build() {
            return new InternetPlan(this);
        }
    }

    /**
     * Returns the internet traffic value.
     *
     * @return Internet traffic value.
     */
    public int getInternetTraffic() {
        return internetTraffic;
    }

    /**
     * Sets the {@link #internetTraffic} value
     * if given value greater or equals to 0.
     *
     * @param internetTrafficValue Value to be set to
     *                             {@link #internetTraffic} value.
     */
    public void setInternetTraffic(final int internetTrafficValue) {
        if (internetTrafficValue >= 0) {
            internetTraffic = internetTrafficValue;
        }
    }

    /**
     * Compares this {@code InternetPlan} to the specified object.
     * Returns {@code true} if and only if the object is not {@code null},
     * and all fields in {@code InternetPlan} object are equals.
     *
     * @param o The object to compare this {@code InternetPlan} against.
     * @return {@code true} if objects are equals.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InternetPlan)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final InternetPlan that = (InternetPlan) o;
        return getInternetTraffic() == that.getInternetTraffic();
    }

    /**
     * Returns a hash code for this object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getInternetTraffic());
    }

    /**
     * Returns the string contains plan description with all parameters.
     *
     * @return The string contains plan description with all parameters.
     */
    @Override
    public String toString() {
        return "Plan{"
                + "planName='" + getPlanName() + '\''
                + ", outgoingWithinNetwork=" + getOutgoingWithinNetwork()
                + ", outgoingOtherNetwork=" + getOutgoingOtherNetwork()
                + ", outgoingAbroad=" + getOutgoingAbroad()
                + ", smsCost=" + getSmsCost()
                + ", megabyteCost=" + getMegabyteCost()
                + ", internetTraffic=" + getInternetTraffic()
                + '}';
    }
}
