package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;

public class PlanCostMegabyteSpecification extends AbstractFindBySpecification<Plan> {

    private double cost;

    public PlanCostMegabyteSpecification(final double costMegabyte) {
        cost = costMegabyte;
    }

    @Override
    public boolean isSatisfiedBy(final Plan plan) {
        return (Double.compare(cost, plan.getMegabyteCost())) == 0;
    }
}
