package by.koshko.task01.entity;

public final class PlanParameters {
    private PlanParameters() {
    }
    /**
     * Numbers represents an index of a parameters in a sequence of parameters.
     */

    //Common parameters inherent for every tariff plan.

    /**
     * Plan type.
     */
    public static final int PLAN_TYPE = 0;
    /**
     * Plan ID.
     */
    public static final int PLAN_ID = 1;
    /**
     * Plan name.
     */
    public static final int PLAN_NAME = 2;
    /**
     * Cost of a minute for calls within network.
     */
    public static final int WITHIN_NETWORK_COST = 3;
    /**
     * Cost of a minute for calls to other network.
     */
    public static final int OTHER_NETWORK_COST = 4;
    /**
     * Cost of a minute for calls abroad.
     */
    public static final int ABROAD_COST = 5;
    /**
     * Cost of an outgoing SMS message.
     */
    public static final int SMS_COST = 6;
    /**
     * Cost of one megabyte of data.
     */
    public static final int MEGABYTE_COST = 7;

    /**
     * Postpaid plan parameter.
     */
    public static final int SUBSCRIPTION_FEE = 8;

    /**
     * Prepaid plan parameter.
     */
    public static final int FAVOURITE_NUMBER = 8;

    //Basic plan parameters.

    /**
     * Amount of free minutes to favourite numbers.
     */
    public static final int MINS_FAVOURITE_NUMBER = 9;
    /**
     * Amount of free sms to favourite numbers.
     */
    public static final int SMS_TO_FAVOURITE_NUMS = 10;

    //Internet plan parameters.

    /**
     * Amount of included internet traffic.
     */
    public static final int INTERNET_TRAFFIC = 9;

    //Social plan parameters.

    /**
     * Amount of free minutes within network.
     */
    public static final int MINUTES_IN_NETWORK = 9;
    /**
     * Amount of free minutes to other networks.
     */
    public static final int MINUTES_OTHER_NETWORK = 10;
    /**
     * Amount of free sms.
     */
    public static final int FREE_SMS = 11;
}
