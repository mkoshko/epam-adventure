package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;

public class PlanIdSpecification extends AbstractFindBySpecification<Plan> {
    /**
     * Condition value.
     */
    private long id;
    /**
     * Creates new {@code PlanCostAbroadSpecification}.
     *
     * @param idValue the value that will be checked for the condition.
     */
    public PlanIdSpecification(final long idValue) {
        id = idValue;
    }
    /**
     * Compares {@link #id} value with one from {@code Plan} object.
     *
     * @param plan {@code Plan} whose value will be used for comparison.
     * @return {@code true} if comparing values are equals.
     */
    @Override
    public boolean isSatisfiedBy(final Plan plan) {
        return (id - plan.getId()) == 0;
    }
}
