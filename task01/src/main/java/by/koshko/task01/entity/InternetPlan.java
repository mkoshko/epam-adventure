package by.koshko.task01.entity;

public class InternetPlan extends PlanWithMonthPayment {
    public static final int REQUIRED_PARAMETERS = 6;
    /**
     * Plan type value.
     */
    private final PlanType type = PlanType.INTERNET;
    /**
     * Amount of included internet traffic that no cost.
     */
    private int includedInternetTraffic;

    /**
     * Creates a new {@code Plan}.
     * @param name Plan name.
     * @param minute Cost per minute.
     * @param megabyte Cost one megabyte.
     * @param sms Cost one sms.
     * @param internetPack Amount of included megabyte.
     * @param price Payment per month.
     */
    public InternetPlan(final String name,
                        final double minute,
                        final double megabyte,
                        final double sms,
                        final int internetPack,
                        final double price) {
        super(name, minute, megabyte, sms, price);
        includedInternetTraffic = internetPack;
    }

    /**
     * Returns amount of included traffic in tariff plan.
     * @return included traffic amount.
     */
    public int getInternetTrafficPack() {
        return includedInternetTraffic;
    }

    /**
     * Sets the {@code int} value {@link #includedInternetTraffic}.
     * Must be greater then 0.
     * @param internetPack amount of included internet traffic.
     */
    public void setInternetTrafficPack(final int internetPack) {
        includedInternetTraffic = includedInternetTraffic;
    }

    /**
     * Returns type of a plan.
     * @return type of a plan.
     */
    @Override
    public PlanType getType() {
        return type;
    }
}
