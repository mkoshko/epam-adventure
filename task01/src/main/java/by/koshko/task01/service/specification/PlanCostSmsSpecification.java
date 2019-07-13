package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;

public class PlanCostSmsSpecification extends AbstractFindBySpecification<Plan> {

    private double cost;

    public PlanCostSmsSpecification(final double costSms) {
        cost = costSms;
    }

    @Override
    public boolean isSatisfiedBy(final Plan plan) {
        return (Double.compare(cost, plan.getSmsCost())) == 0;
    }
}
