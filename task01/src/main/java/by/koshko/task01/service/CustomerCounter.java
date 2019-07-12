package by.koshko.task01.service;

import by.koshko.task01.entity.Plan;

import java.util.List;

public final class CustomerCounter {

    private CustomerCounter() {
    }

    /**
     * Counts the total number of customers.
     *
     * @param plans {@code List} with all available plans.
     * @return The total number of customers.
     */
    public static int countAllCustomers(final List<Plan> plans) {
        int sum = 0;
        for (Plan p : plans) {
            sum += p.getNumberOfUsers();
        }
        return sum;
    }
}
