package by.koshko.task01.entity;

public class CommonPlan {

    public enum PlanType {
        COMMON,
        SOCIAL,
        INTERNET
    }
    public static final int REQUIRED_PARAMETERS = 4;
    /**
     * Plan type value.
     */
    private PlanType type = PlanType.COMMON;
    /**
     * Plan name.
     */
    private String   planName;
    /**
     * Cost per minute call.
     */
    private double   costMinute;
    /**
     * Cost of megabyte internet traffic.
     */
    private double   costMegabyte;
    /**
     * Cost per sms.
     */
    private double   costSms;

    /**
     * Creates a new {@code Plan}.
     * @param name Plan name.
     * @param minute Cost per minute.
     * @param megabyte Cost one megabyte.
     * @param sms Cost one sms.
     */
    public CommonPlan(final String name,
                final double minute,
                final double megabyte,
                final double sms) {
        planName = name;
        costMinute = minute;
        costMegabyte = megabyte;
        costSms = sms;
    }

    /**
     * Returns plan name.
     * @return Plan name.
     */
    public String getPlanName() {
        return planName;
    }

    /**
     * Returns cost per minute call.
     * @return Cost per minute.
     */
    public double getCostMinute() {
        return costMinute;
    }

    /**
     * Returns cost of one megabyte.
     * @return Cost of one megabyte.
     */
    public double getCostMegabyte() {
        return costMegabyte;
    }

    /**
     * Returns cost of one sms.
     * @return Cost of one sms.
     */
    public double getCostSms() {
        return costSms;
    }

    /**
     * Returns plan type.
     * @return plan type.
     */
    public PlanType getType() {
        return type;
    }

    /**
     * Compares this plan to the specified object. The result is {@code
     * true} if and only if the argument is not {@code null} and is a
     * {@code Plan} object that represents the same plan name as this object.
     * @param o The object to compare this {@code Plan} against.
     * @return {@code true} if the given object represents a {@code Plan},
     *         and the plan names equivalent, {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommonPlan plan = (CommonPlan) o;
        return planName.equals(plan.getPlanName());
    }

    /**
     * Returns a hash code for this plan.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 31
                * planName.hashCode()
                * costMinute != 0 ? (int) costMinute : 1
                * costSms != 0 ? (int) costSms : 1
                * costMegabyte != 0 ? (int) costMegabyte : 1;
        return hash;
    }

    /**
     * Returns string description of plans values.
     * @return string description of plans values.
     */
    @Override
    public String toString() {
        return "Plan{"
                + "plan name='" + planName + '\''
                + ", cost minute=" + costMinute
                + ", cost megabyte=" + costMegabyte
                + ", cost sms=" + costSms
                + '}';
    }
}
