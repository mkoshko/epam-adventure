package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;

public class PlanCostInSpecification extends AbstractFindBySpecification<Plan> {

    private double cost;

    public PlanCostInSpecification(final double costInNetwork) {
        cost = costInNetwork;
    }

    @Override
    public boolean isSatisfiedBy(final Plan plan) {
        return (Double.compare(cost, plan.getOutgoingWithinNetwork())) == 0;
    }
}
