package by.koshko.task01.entity;

public abstract class PostpaidPlan extends Plan {

    /**
     * Monthly payment.
     */
    private double subscriptionFee;

    /**
     * Constructor witch uses builder for fields value initialization.
     *
     * @param builder Builder object.
     */
    public PostpaidPlan(final PostpaidBuilder builder) {
        super(builder);
        subscriptionFee = builder.subscriptionFee;
    }

    public static class PostpaidBuilder extends Plan.Builder {
        /**
         * Monthly payment.
         */
        private double subscriptionFee;

        /**
         * Default constructor.
         */
        public PostpaidBuilder() {
        }

        /**
         * Sets the internal {@link #subscriptionFee} value.
         *
         * @param value value to be set to {@link #subscriptionFee}.
         * @return {@code PostpaidBuilder} object.
         */
        public PostpaidBuilder subscription(final double value) {
            subscriptionFee = value;
            return this;
        }
    }

    /**
     * Returns subscription fee value.
     *
     * @return subscription fee value.
     */
    public double getSubscriptionFee() {
        return subscriptionFee;
    }

    /**
     * Sets the {@link #subscriptionFee} value
     * if given value greater or equals to 0.
     *
     * @param subscriptionFeeValue Value to be set to
     *                             {@link #subscriptionFee} value.
     */
    public void setSubscriptionFee(final double subscriptionFeeValue) {
        if (subscriptionFeeValue >= 0) {
            subscriptionFee = subscriptionFeeValue;
        }
    }
}
