package by.koshko.task04.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Storage for {@link Bank} objects.
 */
public final class Banks {
    /**
     * List with {@link  Bank} objects.
     */
    private List<Bank> banks = new ArrayList<>();

    /**
     * Adds {@link Bank} object to {@link #banks} list.
     *
     * @param bank Object to be added to {@link #banks} list.
     */
    public void addBank(final Bank bank) {
        if (bank != null) {
            banks.add(bank);
        }
    }

    /**
     * Returns list of {@link Bank} objects.
     *
     * @return list of {@link Bank} objects.
     */
    public List<Bank> getBanks() {
        return banks;
    }

    @Override
    public String toString() {
        return "Banks{"
                + "banks=" + banks
                + '}';
    }
}
