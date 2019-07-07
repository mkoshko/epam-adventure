package by.koshko.task01.entity;

public abstract class PlanWithMonthPayment extends CommonPlan {

    /**
     * Per month payment.
     */
    private double monthPayment;

    public PlanWithMonthPayment(final String name,
                                final double minute,
                                final double megabyte,
                                final double sms,
                                final double price) {
        super(name, minute, megabyte, sms);
        monthPayment = price;
    }

    /**
     * Returns value payment per month.
     * @return payment per month.
     */
    public double getMonthPayment() {
        return monthPayment;
    }
}
