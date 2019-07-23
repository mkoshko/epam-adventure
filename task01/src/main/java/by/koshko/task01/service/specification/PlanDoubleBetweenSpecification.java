package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;

import java.util.function.Function;

public class PlanDoubleBetweenSpecification
        extends AbstractFindBySpecification<Plan> {

    /**
     * Min value from range.
     */
    private double min;
    /**
     * Max value from range.
     */
    private double max;
    /**
     * Function uses to obtain value from {@code Plan} object.
     */
    private Function<Plan, Double> func;

    /**
     * Creates new {@code PlanDoubleBetweenSpecification}.
     *
     * @param minValue Min value from range.
     * @param maxValue Max value from range.
     * @param function Function uses to obtain value from {@code Plan} object.
     */
    public PlanDoubleBetweenSpecification(
            final double minValue,
            final double maxValue,
            final Function<Plan, Double> function) {
        min = minValue;
        max = maxValue;
        func = function;
    }

    /**
     * Checks if value obtained with {@link #func} function from {@code Plan} is
     * in range between {@link #min} and {@link #max} value.
     *
     * @param plan {@code Plan} object from which the value for comparing
     *             obtained.
     * @return {@code true} if value is in specified range,
     * {@code false} otherwise.
     */
    @Override
    public boolean isSatisfiedBy(final Plan plan) {
        return (Double.compare(func.apply(plan), min) >= 0)
                && (Double.compare(func.apply(plan), max) <= 0);
    }
}
