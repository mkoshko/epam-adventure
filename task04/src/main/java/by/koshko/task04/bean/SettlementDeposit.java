package by.koshko.task04.bean;

public class SettlementDeposit extends Deposit {
    /**
     * Minimal balance of the deposit.
     */
    private double minBalance;

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(final double newMinBalance) {
        minBalance = newMinBalance;
    }

    @Override
    public String toString() {
        return super.toString()
                + "minBalance=" + minBalance
                + '}';
    }
}
