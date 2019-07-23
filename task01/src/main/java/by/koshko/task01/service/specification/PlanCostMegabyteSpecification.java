package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;

public class PlanCostMegabyteSpecification
        extends AbstractFindBySpecification<Plan> {
    /**
     * Condition value.
     */
    private double cost;

    /**
     * Creates new {@code PlanCostAbroadSpecification}.
     *
     * @param costMegabyte the value that will be checked for the condition.
     */
    public PlanCostMegabyteSpecification(final double costMegabyte) {
        cost = costMegabyte;
    }

    /**
     * Compares {@link #cost} value with one from {@code Plan} object.
     *
     * @param plan {@code Plan} whose value will be used for comparison.
     * @return {@code true} if comparing values are equals.
     */
    @Override
    public boolean isSatisfiedBy(final Plan plan) {
        return (Double.compare(cost, plan.getMegabyteCost())) == 0;
    }
}
