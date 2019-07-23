package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;

public class PlanNameStartWithSpecification
        extends AbstractFindBySpecification<Plan> {

    /**
     * Condition value.
     */
    private String prefix;
    /**
     * Creates new {@code PlanCostAbroadSpecification}.
     *
     * @param prefixValue the value that will be checked for the condition.
     */
    public PlanNameStartWithSpecification(final String prefixValue) {
        prefix = prefixValue;
    }
    /**
     * Checks if {@code Plan} name starts with the {@link #prefix}.
     *
     * @param plan {@code Plan} whose value will be used for checking condition.
     * @return {@code true} if {@code Plan} name starts with {@link #prefix}.
     */
    @Override
    public boolean isSatisfiedBy(final Plan plan) {
        return plan.getPlanName().startsWith(prefix);
    }
}
