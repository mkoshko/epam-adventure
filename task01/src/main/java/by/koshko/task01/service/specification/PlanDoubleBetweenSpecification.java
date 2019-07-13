package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;

import java.util.function.Function;

public class PlanDoubleBetweenSpecification extends AbstractFindBySpecification<Plan> {

    private double min;
    private double max;
    private Function<Plan, Double> func;

    public PlanDoubleBetweenSpecification(final double minValue,
                                          final double maxValue,
                                          final Function<Plan, Double> function) {
        min = minValue;
        max = maxValue;
        func = function;
    }

    @Override
    public boolean isSatisfiedBy(final Plan plan) {
        return (Double.compare(func.apply(plan), min) >= 0)
                && (Double.compare(func.apply(plan), max) <= 0);
    }
}
