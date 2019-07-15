package by.koshko.task01.entity;

public class SocialPlan extends PostpaidPlan {
    /**
     * Amount of required argument for object creation.
     */
    public static final int REQUIRED_ARGUMENTS = 11;
    /**
     * Type of a plan.
     */
    public static final PlanType TYPE = PlanType.SOCIAL;
    /**
     * Amount of free minutes to call within network.
     */
    private int minutesInNetwork;
    /**
     * Amount of free minutes to call to other networks.
     */
    private int minutesOtherNetwork;
    /**
     * Amount of free sms.
     */
    private int freeSms;

    /**
     * Constructor witch obtains values from {@code Builder} object.
     *
     * @param builder {@code Builder} object.
     */
    public SocialPlan(final SocialPlanBuilder builder) {
        super(builder);
        minutesInNetwork = builder.minutesInNetwork;
        minutesOtherNetwork = builder.minutesOtherNetwork;
        freeSms = builder.freeSms;
    }

    public static class SocialPlanBuilder extends PostpaidBuilder {

        /**
         * Amount of free minutes to call within network.
         */
        private int minutesInNetwork;
        /**
         * Amount of free minutes to call to other networks.
         */
        private int minutesOtherNetwork;
        /**
         * Amount of free sms.
         */
        private int freeSms;

        /**
         * Default constructor.
         */
        public SocialPlanBuilder() {
        }

        /**
         * Sets the internal {@link #minutesInNetwork} value.
         *
         * @param value value to be set to {@link #minutesInNetwork}.
         * @return {@code BasicPlanBuilder} object.
         */
        public SocialPlanBuilder minutesIn(final int value) {
            minutesInNetwork = value;
            return this;
        }

        /**
         * Sets the internal {@link #minutesOtherNetwork} value.
         *
         * @param value value to be set to {@link #minutesOtherNetwork}.
         * @return {@code BasicPlanBuilder} object.
         */
        public SocialPlanBuilder minutesOther(final int value) {
            minutesOtherNetwork = value;
            return this;
        }

        /**
         * Sets the internal {@link #freeSms} value.
         *
         * @param value value to be set to {@link #freeSms}.
         * @return {@code BasicPlanBuilder} object.
         */
        public SocialPlanBuilder freeSms(final int value) {
            freeSms = value;
            return this;
        }

        /**
         * Invokes creation of {@code SocialPlan} object.
         *
         * @return {@code SocialPlan} instance.
         */
        public SocialPlan build() {
            return new SocialPlan(this);
        }
    }
    //END OF BUILDER

    /**
     * Returns amount of minutes for calls within network.
     *
     * @return amount of minutes for calls within network.
     */
    public int getMinutesInNetwork() {
        return minutesInNetwork;
    }

    /**
     * Sets the {@link #minutesInNetwork} value
     * if given value greater or equals to 0.
     *
     * @param minutesInNetworkValue Value to be set to
     *                              {@link #minutesInNetwork} value.
     */
    public void setMinutesInNetwork(final int minutesInNetworkValue) {
        minutesInNetwork =
                minutesInNetworkValue >= 0
                        ? minutesInNetworkValue : minutesOtherNetwork;
    }

    /**
     * Returns amount of minutes for calls to other networks.
     *
     * @return amount of minutes for calls to other networks.
     */
    public int getMinutesOtherNetwork() {
        return minutesOtherNetwork;
    }

    /**
     * Sets the {@link #minutesOtherNetwork} value
     * if given value greater or equals to 0.
     *
     * @param minutesOtherNetworkValue Value to be set to
     *                                 {@link #minutesOtherNetwork} value.
     */
    public void setMinutesOtherNetwork(final int minutesOtherNetworkValue) {
        minutesOtherNetwork =
                minutesOtherNetworkValue >= 0
                        ? minutesOtherNetworkValue : minutesOtherNetwork;
    }

    /**
     * Returns amount of free sms.
     *
     * @return amount of free sms.
     */
    public int getFreeSms() {
        return freeSms;
    }

    /**
     * Sets the {@link #freeSms} value
     * if given value greater or equals to 0.
     *
     * @param freeSmsValue Value to be set to
     *                     {@link #freeSms} value.
     */
    public void setFreeSms(final int freeSmsValue) {
        freeSms = freeSmsValue >= 0 ? freeSmsValue : freeSms;
    }

    /**
     * Returns the string contains plan description with all parameters.
     *
     * @return The string contains plan description with all parameters.
     */
    public String toString() {
        return "SocialPlan{"
                + "plan id=" + getId()
                + ", plan name='" + getPlanName() + '\''
                + ", outgoing within network cost=" + getOutgoingWithinNetwork()
                + ", outgoing other networks cost=" + getOutgoingOtherNetwork()
                + ", outgoing abroad=" + getOutgoingAbroad()
                + ", sms cost=" + getSmsCost()
                + ", megabyte cost=" + getMegabyteCost()
                + ", free minutes within network=" + minutesInNetwork
                + ", free minutes to other Networks=" + minutesOtherNetwork
                + ", free sms=" + freeSms
                + '}';
    }
}
