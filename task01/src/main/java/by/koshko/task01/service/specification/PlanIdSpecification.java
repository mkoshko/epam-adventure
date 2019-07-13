package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;

public class PlanIdSpecification extends AbstractFindBySpecification<Plan> {

    private long id;

    public PlanIdSpecification(final long idValue) {
        id = idValue;
    }

    @Override
    public boolean isSatisfiedBy(final Plan plan) {
        return (id - plan.getId()) == 0;
    }
}
