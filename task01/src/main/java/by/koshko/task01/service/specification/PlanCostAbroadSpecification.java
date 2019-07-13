package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;

public class PlanCostAbroadSpecification extends AbstractFindBySpecification<Plan> {

    private double cost;

    public PlanCostAbroadSpecification(final double costAbroad) {
        cost = costAbroad;
    }

    @Override
    public boolean isSatisfiedBy(final Plan plan) {
        return (Double.compare(cost, plan.getOutgoingAbroad())) == 0;
    }
}
