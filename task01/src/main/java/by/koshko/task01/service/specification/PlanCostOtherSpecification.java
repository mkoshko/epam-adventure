package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;

public class PlanCostOtherSpecification extends AbstractFindBySpecification<Plan> {

    private double cost;

    public PlanCostOtherSpecification(final double costOtherNetwork) {
        cost = costOtherNetwork;
    }

    @Override
    public boolean isSatisfiedBy(final Plan plan) {
        return (Double.compare(cost, plan.getOutgoingOtherNetwork())) == 0;
    }
}
