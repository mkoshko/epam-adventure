package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;

public class PlanNameSpecification extends AbstractFindBySpecification<Plan> {

    private String planName;

    public PlanNameSpecification(final String name) {
        planName = name;
    }

    @Override
    public boolean isSatisfiedBy(final Plan plan) {
        return plan.getPlanName().equals(planName);
    }
}
