package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;

public class PlanCostInBetweenSpecification extends AbstractFindBySpecification<Plan> {

    private double min;
    private double max;

    public PlanCostInBetweenSpecification(final double minValue, final double maxValue) {
        min = minValue;
        max = maxValue;
    }

    @Override
    public boolean isSatisfiedBy(final Plan plan) {
        return (Double.compare(plan.getOutgoingWithinNetwork(), min) >= 0)
                && (Double.compare(plan.getOutgoingWithinNetwork(), max) <= 0);
    }
}
