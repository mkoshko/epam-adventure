package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;

public class PlanNameStartWithSpecification extends AbstractFindBySpecification<Plan> {

    private String prefix;

    public PlanNameStartWithSpecification(final String prefixValue) {
        prefix = prefixValue;
    }

    @Override
    public boolean isSatisfiedBy(final Plan plan) {
        return plan.getPlanName().startsWith(prefix);
    }
}
