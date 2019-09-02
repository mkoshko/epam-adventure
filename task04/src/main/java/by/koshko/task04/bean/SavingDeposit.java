package by.koshko.task04.bean;

public class SavingDeposit extends Deposit {
    /**
     * Type of deposit payout.
     */
    private String payout;

    public String getPayout() {
        return payout;
    }

    public void setPayout(final String newPayout) {
        payout = newPayout;
    }


    @Override
    public String toString() {
        return super.toString()
                + "payout='" + payout + '\''
                + '}';
    }
}
