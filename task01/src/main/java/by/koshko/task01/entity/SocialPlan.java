package by.koshko.task01.entity;

public class SocialPlan extends PlanWithMonthPayment {
    public static final int REQUIRED_PARAMETERS = 8;
    /**
     * Plan type value.
     */
    private final PlanType type = PlanType.SOCIAL;
    /**
     * Amount of included minutes for calls in other networks for free.
     */
    private int otherNetworksMinutes;
    /**
     * Amount of included minutes for calls within networks for free.
     */
    private int withinNetworkMinutes;
    /**
     * Amount of included sms that can be sent for free.
     */
    private int smsPack;

    /**
     * Creates a new {@code SocialPlan}.
     * @param name Plan name.
     * @param minute Cost per minute.
     * @param megabyte Cost one megabyte.
     * @param sms Cost one sms.
     * @param otherNetworks Amount of free minutes for calls in other networks.
     * @param withinNetwork Amount of free minutes for calls within networks.
     * @param includedSms Amount of free sms to be sent.
     * @param price Payment per month.
     */
    public SocialPlan(final String name,
                      final double minute,
                      final double megabyte,
                      final double sms,
                      final double price,
                      final int otherNetworks,
                      final int withinNetwork,
                      final int includedSms) {
        super(name, minute, megabyte, sms, price);
        otherNetworksMinutes = otherNetworks;
        withinNetworkMinutes = withinNetwork;
        smsPack = includedSms;
    }

    public int getOtherNetworksMinutes() {
        return otherNetworksMinutes;
    }

    public void setOtherNetworksMinutes(final int otherNetworks) {
        otherNetworksMinutes = otherNetworks;
    }

    public int getWithinNetworkMinutes() {
        return withinNetworkMinutes;
    }

    public void setWithinNetworkMinutes(final int withinNetwork) {
        withinNetworkMinutes = withinNetwork;
    }

    public int getSmsPack() {
        return smsPack;
    }

    public void setSmsPack(final int sms) {
        smsPack = sms;
    }
}
