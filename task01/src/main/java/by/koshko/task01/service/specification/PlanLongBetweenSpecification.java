package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;

import java.util.function.Function;

public class PlanLongBetweenSpecification
        extends AbstractFindBySpecification<Plan> {
    /**
     * Min value from range.
     */
    private long min;
    /**
     * Max value from range.
     */
    private long max;
    /**
     * Function uses to obtain value from {@code Plan} object.
     */
    private Function<Plan, Long> func;

    /**
     * Creates new {@code PlanDoubleBetweenSpecification}.
     *
     * @param minValue Min value from range.
     * @param maxValue Max value from range.
     * @param function Function uses to obtain value from {@code Plan} object.
     */
    public PlanLongBetweenSpecification(final long minValue,
                                       final long maxValue,
                                       final Function<Plan, Long> function) {
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
        return (func.apply(plan) >= min)
                && (func.apply(plan) <= max);
    }
}
