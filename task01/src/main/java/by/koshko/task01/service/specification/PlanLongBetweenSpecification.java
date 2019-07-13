package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;

import java.util.function.Function;

public class PlanLongBetweenSpecification extends AbstractFindBySpecification<Plan> {

    private long min;
    private long max;
    private Function<Plan, Long> func;

    public PlanLongBetweenSpecification(final int minvalue,
                                       final int maxValue,
                                       final Function<Plan, Long> function) {
        min = minvalue;
        max = maxValue;
        func = function;
    }

    @Override
    public boolean isSatisfiedBy(final Plan plan) {
        return (func.apply(plan) >= min)
                && (func.apply(plan) <= max);
    }
}
