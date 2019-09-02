package by.koshko.task04.bean;

/**
 * Contains types for {@link Deposit} class.
 */
public enum DepositType {
    /**
     * Demand deposit.
     */
    DEMAND("До востребования"),
    /**
     * Term deposit.
     */
    TERM("Срочный"),
    /**
     * Saving deposit.
     */
    SAVING("Сберегательный"),
    /**
     * Cumulative deposit.
     */
    CUMULATIVE("Накопительный"),
    /**
     * Settlement deposit.
     */
    SETTLEMENT("Расчетный");

    /**
     * Type of a deposit.
     */
    private String type;

    /**
     * Sets the type of the deposit.
     *
     * @param depositType type to be set.
     */
    DepositType(final String depositType) {
        type = depositType;
    }

    /**
     * Returns type of the deposit.
     *
     * @return type of the deposit.
     */
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "DepositType{"
                + "type='" + type + '\''
                + '}';
    }
}
