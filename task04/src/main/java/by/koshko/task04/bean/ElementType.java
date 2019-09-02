package by.koshko.task04.bean;

public enum ElementType {
    BANKS,
    BANK,
    NAME,
    DEPOSIT,
    COUNTRY,
    SITE,
    LICENCE,
    TYPE,
    IBAN,
    DEPOSITOR,
    DEPOSITDATE,
    AMOUNT,
    CURRENCY,
    PROFITABILITY,
    TERM,
    WITHDRAWAL,
    REFILL,
    CAPITALIZATION,
    ID,
    SAVINGDEPOSIT,
    PAYOUT,
    SELLTEMENTDEPOSIT,
    MINBALANCE,
    FIRSTNAME,
    MIDDLENAME,
    LASTNAME,
    IDENTIFICATION;

    public static ElementType fromValue(final String value) {
        for (ElementType t : ElementType.values()) {
            if (t.name().equalsIgnoreCase(value)) {
                return t;
            }
        }
        throw new IllegalArgumentException(value);
    }
}
