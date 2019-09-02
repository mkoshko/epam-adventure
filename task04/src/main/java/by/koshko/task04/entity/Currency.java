package by.koshko.task04.entity;

/**
 * Currencies for {@code Deposit}.
 */
public enum Currency {
    /**
     * Russian ruble.
     */
    RUB,
    /**
     * Belarussian ruble.
     */
    BYR,
    /**
     * United States Dollar.
     */
    USD,
    /**
     * Euro.
     */
    EUR;

    /**
     * Returns {@code Currency} if exists.
     *
     * @param value to be compares with all enum types.
     * @return enum value if any found.
     */
    public static Currency fromValue(final String value) {
        for (Currency c : Currency.values()) {
            if (c.name().equals(value)) {
                return c;
            }
        }
        throw new IllegalArgumentException(value);
    }
}
