package by.koshko.task01.entity;

public final class PlanParameters {
    // PLAN PARAMETERS
    public static final int PLAN_TYPE             = 0;
    public static final int PLAN_ID               = 1;
    public static final int PLAN_NAME             = 2;
    public static final int WITHIN_NETWORK_COST   = 3;
    public static final int OTHER_NETWORK_COST    = 4;
    public static final int ABROAD_COST           = 5;
    public static final int SMS_COST              = 6;
    public static final int MEGABYTE_COST         = 7;
    // END

    //POSTPAID PLAN PARAMETERS
    public static final int SUBSCRIPTION_FEE      = 8;
    // END

    // PREPAID PLAN PARAMETERS
    public static final int FAVOURITE_NUMBER      = 8;
    //END

    //BASIC PLAN PARAMETERS
    public static final int MINS_FAVOURITE_NUMBER = 9;
    public static final int SMS_TO_FAVOURITE_NUMS = 10;
    // END

    //INTERNET PLAN PARAMETERS
    public static final int INTERNET_TRAFFIC      = 9;
    //END

    //SOCIAL PLAN PARAMETERS
    public static final int MINUTES_IN_NETWORK    = 9;
    public static final int MINUTES_OTHER_NETWORK = 10;
    public static final int FREE_SMS              = 11;
    //END
    private PlanParameters() {
    }
}
