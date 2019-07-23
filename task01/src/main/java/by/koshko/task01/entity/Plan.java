package by.koshko.task01.entity;

import by.koshko.task01.service.Publisher;
import by.koshko.task01.service.Subscriber;

import java.util.LinkedList;
import java.util.List;

public abstract class Plan implements Publisher {
    public enum PlanType {
        /**
         * Indicates that a plan most likely for internet surfing.
         */
        INTERNET,
        /**
         * Indicates that a plan most likely for calls and sms.
         */
        SOCIAL,
        /**
         * Indicates a plan with no monthly payment.
         */
        BASIC
    }

    /**
     * List of subscribers which will notified if some updates occurs.
     */
    private List<Subscriber> subscribers = new LinkedList<>();
    /**
     * Identifier of a plan.
     */
    private long id;
    /**
     * Name of the plan.
     */
    private String planName;
    /**
     * Cost of a minute for calls within network.
     */
    private double outgoingWithinNetwork;
    /**
     * Cost of a minute for calls to other network.
     */
    private double outgoingOtherNetwork;
    /**
     * Cost of a minute for calls abroad.
     */
    private double outgoingAbroad;
    /**
     * Cost of an outgoing SMS message.
     */
    private double smsCost;
    /**
     * Cost of one megabyte of data.
     */
    private double megabyteCost;
    /**
     * Amount of customers using the plan.
     */
    private int numberOfUsers;

    /**
     * Default empty constructor.
     */
    public Plan() {
    }
    /**
     * Constructor witch obtains values from builder object.
     *
     * @param builder {@code Builder} object.
     */
    public Plan(final Builder builder) {
        id = builder.id;
        planName = builder.planName;
        outgoingWithinNetwork = builder.outgoingWithinNetwork;
        outgoingOtherNetwork = builder.outgoingOtherNetwork;
        outgoingAbroad = builder.outgoingAbroad;
        smsCost = builder.smsCost;
        megabyteCost = builder.megabyteCost;
    }

    public static class Builder {
        /**
         * Identifier of a plan.
         */
        private long id;
        /**
         * Name of the plan.
         */
        private String planName;
        /**
         * Cost of a minute for calls within network.
         */
        private double outgoingWithinNetwork;
        /**
         * Cost of a minute for calls to other network.
         */
        private double outgoingOtherNetwork;
        /**
         * Cost of a minute for calls abroad.
         */
        private double outgoingAbroad;
        /**
         * Cost of an outgoing SMS message.
         */
        private double smsCost;
        /**
         * Cost of one megabyte of data.
         */
        private double megabyteCost;

        /**
         * Default constructor.
         */
        public Builder() {
        }

        /**
         * Sets the internal {@link #id} value.
         *
         * @param value value to be set to {@link #id}.
         * @return {@code Builder} object.
         */
        public Builder id(final long value) {
            id = value;
            return this;
        }

        /**
         * Sets the internal {@link #planName} value.
         *
         * @param value value to be set to {@link #planName}.
         * @return {@code Builder} object.
         */
        public Builder planName(final String value) {
            planName = value;
            return this;
        }

        /**
         * Sets the internal {@link #outgoingWithinNetwork} value.
         *
         * @param value value to be set to {@link #outgoingWithinNetwork}.
         * @return {@code Builder} object.
         */
        public Builder outgoingIn(final double value) {
            outgoingWithinNetwork = value;
            return this;
        }

        /**
         * Sets the internal {@link #outgoingOtherNetwork} value.
         *
         * @param value value to be set to {@link #outgoingOtherNetwork}.
         * @return {@code Builder} object.
         */
        public Builder outgoingOther(final double value) {
            outgoingOtherNetwork = value;
            return this;
        }

        /**
         * Sets the internal {@link #outgoingAbroad} value.
         *
         * @param value value to be set to {@link #outgoingAbroad}.
         * @return {@code Builder} object.
         */
        public Builder outgoingAbroad(final double value) {
            outgoingAbroad = value;
            return this;
        }

        /**
         * Sets the internal {@link #smsCost} value.
         *
         * @param value value to be set to {@link #smsCost}.
         * @return {@code Builder} object.
         */
        public Builder smsCost(final double value) {
            smsCost = value;
            return this;
        }

        /**
         * Sets the internal {@link #megabyteCost} value.
         *
         * @param value value to be set to {@link #megabyteCost}.
         * @return {@code Builder} object.
         */
        public Builder megabyteCost(final double value) {
            megabyteCost = value;
            return this;
        }
    }
    // END OF BUILDER

    /**
     * Returns the plan id.
     *
     * @return the plan id.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the {@link #id} value if given value greater or equals to 0.
     *
     * @param idValue Value to be set to plan id.
     */
    public void setId(final long idValue) {
        id = idValue >= 0 ? idValue : id;
    }

    /**
     * Sets the {@link #planName} value if given value in not {@code null},
     * and name length greater then 0.
     *
     * @param planNameValue Value to be set to plan name.
     */
    public void setPlanName(final String planNameValue) {
        if (planNameValue != null && planNameValue.length() > 0) {
            planName = planNameValue;
        }
    }

    /**
     * Sets the {@link #outgoingWithinNetwork} value
     * if given value greater or equals to 0.
     *
     * @param outgoingWithinNetworkValue Value to be set to
     *                                   {@link #outgoingWithinNetwork} value.
     */
    public void setOutgoingWithinNetwork(
            final double outgoingWithinNetworkValue) {
        if (outgoingWithinNetworkValue >= 0) {
            outgoingWithinNetwork = outgoingWithinNetworkValue;
        }
    }

    /**
     * Sets the {@link #outgoingOtherNetwork} value
     * if given value greater or equals to 0.
     *
     * @param outgoingOtherNetworkValue Value to be set to
     *                                  {@link #outgoingOtherNetwork} value.
     */
    public void setOutgoingToOtherNetwork(
            final double outgoingOtherNetworkValue) {
        if (outgoingOtherNetworkValue >= 0) {
            outgoingOtherNetwork = outgoingOtherNetworkValue;
        }
    }

    /**
     * Sets the {@link #outgoingAbroad} value
     * if given value greater or equals to 0.
     *
     * @param outgoingAbroadValue Value to be set to
     *                            {@link #outgoingAbroad} value.
     */
    public void setOutgoingAbroad(final double outgoingAbroadValue) {
        if (outgoingAbroadValue >= 0) {
            outgoingAbroad = outgoingAbroadValue;
        }
    }

    /**
     * Sets the {@link #smsCost} value
     * if given value greater or equals to 0.
     *
     * @param smsCostValue Value to be set to {@link #smsCost} value.
     */
    public void setSmsCost(final double smsCostValue) {
        if (smsCostValue >= 0) {
            smsCost = smsCostValue;
        }
    }

    /**
     * Sets the {@link #megabyteCost} value
     * if given value greater or equals to 0.
     *
     * @param megabyteCostValue Value to be set to {@link #megabyteCost} value.
     */
    public void setMegabyteCost(final double megabyteCostValue) {
        if (megabyteCostValue >= 0) {
            megabyteCost = megabyteCostValue;
        }
    }

    /**
     * Supposed to using by some service for keep tracking of total number of
     * customers which should get current number of customers then calculate new
     * value and pass it into this method.
     * Sets the {@link #numberOfUsers} value
     * if given value greater or equals to 0.
     *
     * @param numberOfUsersValue Value to be set to
     *                           {@link #numberOfUsers} value.
     */
    public void setNumberOfUsers(final int numberOfUsersValue) {
        if (numberOfUsersValue >= 0) {
            numberOfUsers = numberOfUsersValue;
            notifySubscribers();
        }
    }

    /**
     * Adds {@code Subscriber} to {@link #subscribers}.
     * @param subscriber {@code Subscriber} to be added to {@link #subscribers}.
     * @return {@code true} if {@code Subscriber} was successfully added,
     *          {@code false} otherwise.
     */
    @Override
    public boolean subscribe(final Subscriber subscriber) {
        if (!subscribers.contains(subscriber)) {
            return subscribers.add(subscriber);
        }
        return false;
    }

    /**
     * Removes {@code Subscriber} from {@link #subscribers}.
     * @param subscriber {@code Subscriber} to be removed from
     *                   {@link #subscribers}.
     * @return {@code true} if {@code Subscriber} was successfully removed,
     *         {@code false} otherwise.
     */
    @Override
    public boolean unsubscribe(final Subscriber subscriber) {
        return subscribers.remove(subscriber);
    }

    /**
     * Notifies all {@code Subscriber} from {@link #subscribers}.
     */
    @Override
    public void notifySubscribers() {
        subscribers.forEach(subscriber -> subscriber.onUpdate(this));
    }

    /**
     * Returns the name of the plan.
     *
     * @return the name of the plan.
     */
    public String getPlanName() {
        return planName;
    }

    /**
     * Returns the cost of outgoing calls within networks.
     *
     * @return the cost of outgoing calls within networks.
     */
    public double getOutgoingWithinNetwork() {
        return outgoingWithinNetwork;
    }

    /**
     * Returns the cost of outgoing calls to other networks.
     *
     * @return the cost of outgoing calls to other networks.
     */
    public double getOutgoingOtherNetwork() {
        return outgoingOtherNetwork;
    }

    /**
     * Returns the cost of outgoing calls abroad.
     *
     * @return the cost of outgoing calls abroad.
     */
    public double getOutgoingAbroad() {
        return outgoingAbroad;
    }

    /**
     * Returns the cost of outgoing sms message.
     *
     * @return the cost of outgoing sms message.
     */
    public double getSmsCost() {
        return smsCost;
    }

    /**
     * Returns the cost of megabyte of data.
     *
     * @return the cost of megabyte of data.
     */
    public double getMegabyteCost() {
        return megabyteCost;
    }

    /**
     * Returns amount of customers using the plan.
     *
     * @return Amount of customers using the plan.
     */
    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    /**
     * Compares this {@code Plan} to the specified object.
     * Returns {@code true} if and only if the object is not {@code null},
     * and all fields in {@code Plan} object are equals.
     *
     * @param o The object to compare this {@code Plan} against.
     * @return {@code true} if given object are {@code Plan},
     * and all field are equals, {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Plan)) {
            return false;
        }
        Plan plan = (Plan) o;
        return Double.compare(plan.getOutgoingWithinNetwork(),
                getOutgoingWithinNetwork()) == 0
                && Double.compare(plan.getOutgoingOtherNetwork(),
                getOutgoingOtherNetwork()) == 0
                && Double.compare(plan.getOutgoingAbroad(),
                getOutgoingAbroad()) == 0
                && Double.compare(plan.getSmsCost(),
                getSmsCost()) == 0
                && Double.compare(plan.getMegabyteCost(),
                getMegabyteCost()) == 0
                && getPlanName().equals(plan.getPlanName());

    }

    /**
     * Returns a hash code for this object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        final int h = 31;
        int hash = 0;
        hash = h * hash + (planName == null ? 0 : planName.hashCode());
        hash = h * hash + Double.valueOf(outgoingWithinNetwork).hashCode();
        hash = h * hash + Double.valueOf(outgoingOtherNetwork).hashCode();
        hash = h * hash + Double.valueOf(outgoingAbroad).hashCode();
        hash = h * hash + Double.valueOf(smsCost).hashCode();
        hash = h * hash + Double.valueOf(megabyteCost).hashCode();
        return hash;
    }

    /**
     * Returns the string contains plan description with all parameters.
     *
     * @return The string contains plan description with all parameters.
     */
    @Override
    public String toString() {
        return "Plan{"
                + "planID=" + id
                + ", planName='" + planName + '\''
                + ", outgoingWithinNetwork=" + outgoingWithinNetwork
                + ", outgoingOtherNetwork=" + outgoingOtherNetwork
                + ", outgoingAbroad=" + outgoingAbroad
                + ", smsCost=" + smsCost
                + ", megabyteCost=" + megabyteCost
                + '}';
    }
}
