package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;

import java.util.function.Function;

public class PlanIntBetweenSpecification extends AbstractFindBySpecification<Plan> {

    private int min;
    private int max;
    private Function<Plan, Integer> func;

    public PlanIntBetweenSpecification(final int minValue,
                                       final int maxValue,
                                       final Function<Plan, Integer> function) {
        this.min = minValue;
        this.max = maxValue;
        this.func = function;
    }

    @Override
    public boolean isSatisfiedBy(final Plan plan) {
        return (func.apply(plan) >= min)
                && (func.apply(plan) <= max);
    }
}
