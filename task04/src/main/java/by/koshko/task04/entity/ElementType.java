package by.koshko.task04.entity;

/**
 * All elements from {code banks.xsd}.
 */
public enum ElementType {
    /**
     * <banks> element.
     */
    BANKS,
    /**
     * <bank> element.
     */
    BANK,
    /**
     * <name> element.
     */
    NAME,
    /**
     * <deposit> element.
     */
    DEPOSIT,
    /**
     * country attribute.
     */
    COUNTRY,
    /**
     * site attribute.
     */
    SITE,
    /**
     * licence attribute.
     */
    LICENCE,
    /**
     * <type> element.
     */
    TYPE,
    /**
     * <iban> element.
     */
    IBAN,
    /**
     * <depositor> element.
     */
    DEPOSITOR,
    /**
     * <depositDate> element.
     */
    DEPOSITDATE,
    /**
     * <amount> element.
     */
    AMOUNT,
    /**
     * <currency> element.
     */
    CURRENCY,
    /**
     * <profitability> element.
     */
    PROFITABILITY,
    /**
     * <term> element.
     */
    TERM,
    /**
     * <withdrawal> element.
     */
    WITHDRAWAL,
    /**
     * <refill> element.
     */
    REFILL,
    /**
     * <capitalization> element.
     */
    CAPITALIZATION,
    /**
     * id attribute.
     */
    ID,
    /**
     * <savingDeposit> element.
     */
    SAVINGDEPOSIT,
    /**
     * <payout> element.
     */
    PAYOUT,
    /**
     * <settlementDeposit> element.
     */
    SELLTEMENTDEPOSIT,
    /**
     * <minBalance> element.
     */
    MINBALANCE,
    /**
     * <firstName> element.
     */
    FIRSTNAME,
    /**
     * <middleName> element.
     */
    MIDDLENAME,
    /**
     * <lastName> element.
     */
    LASTNAME,
    /**
     * <identification> element.
     */
    IDENTIFICATION;

    /**
     * Returns enum type value with the same name given from string argument.
     *
     * @param value Value to be returned as enum type.
     * @return enum type value if that exist, {@code null} otherwise.
     */
    public static ElementType fromValue(final String value) {
        for (ElementType t : ElementType.values()) {
            if (t.name().equalsIgnoreCase(value)) {
                return t;
            }
        }
        return null;
    }
}
