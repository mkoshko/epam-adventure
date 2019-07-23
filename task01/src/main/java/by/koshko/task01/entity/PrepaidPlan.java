package by.koshko.task01.entity;

public abstract class PrepaidPlan extends Plan {
    /**
     * Amount of available favourite numbers.
     */
    private int favouriteNumbers;

    /**
     * Default empty constructor.
     */
    public PrepaidPlan() {
        super();
    }
    /**
     * Constructor witch obtains values from {@code Builder} object.
     *
     * @param builder {@code Builder} object.
     */
    public PrepaidPlan(final PrepaidPlanBuilder builder) {
        super(builder);
        favouriteNumbers = builder.favouriteNumbers;
    }

    public static class PrepaidPlanBuilder extends Plan.Builder {
        /**
         * Amount of available favourite numbers.
         */
        private int favouriteNumbers;

        /**
         * Default constructor.
         */
        public PrepaidPlanBuilder() {
        }

        /**
         * Sets the internal {@link #favouriteNumbers} value.
         *
         * @param value value to be set to {@link #favouriteNumbers}.
         * @return {@code PrepaidPlanBuilder} object.
         */
        public PrepaidPlanBuilder favouriteNumbers(final int value) {
            favouriteNumbers = value;
            return this;
        }
    }

    /**
     * Returns amount of favourite numbers.
     *
     * @return amount of favourite numbers.
     */
    public int getFavouriteNumbers() {
        return favouriteNumbers;
    }

    /**
     * Sets the {@link #favouriteNumbers} value
     * if given value greater or equals to 0.
     *
     * @param favouriteNumbersValue Value to be set to
     *                              {@link #favouriteNumbers} value.
     */
    public void setFavouriteNumbers(final int favouriteNumbersValue) {
        if (favouriteNumbersValue >= 0) {
            favouriteNumbers = favouriteNumbersValue;
        }
    }
}
