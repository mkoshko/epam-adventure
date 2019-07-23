package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;

public class PlanNameSpecification extends AbstractFindBySpecification<Plan> {
    /**
     * Condition value.
     */
    private String planName;

    /**
     * Creates new {@code PlanCostAbroadSpecification}.
     *
     * @param name the value that will be checked for the condition.
     */
    public PlanNameSpecification(final String name) {
        planName = name;
    }

    /**
     * Compares {@link #planName} value with one from {@code Plan} object.
     *
     * @param plan {@code Plan} whose value will be used for comparison.
     * @return {@code true} if comparing values are equals.
     */
    @Override
    public boolean isSatisfiedBy(final Plan plan) {
        return plan.getPlanName().equals(planName);
    }
}
