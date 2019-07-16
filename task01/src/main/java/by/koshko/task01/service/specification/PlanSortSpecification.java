package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class PlanSortSpecification implements SortBySpecification<Plan> {

    /**
     * {@code Comparator} used for {@link List} sorting.
     */
    private Comparator comparator;

    /**
     * Constructor that accept {@link Function} and initialize
     * {@link #comparator} filed.
     *
     * @param function is a {@link Function} to be passed
     *                 to {@link Comparator#comparing(Function)}.
     */
    @SuppressWarnings("unchecked")
    public PlanSortSpecification(final Function<? super Plan,
            ? extends Comparable> function) {

        comparator = Comparator.comparing(function);
    }

    /**
     * Constructor that accept {@code Function} and initialize
     * {@link #comparator} filed.
     *
     * @param function1 is a {@link Function} to be passed to
     *                  {@link Comparator#comparing(Function)}.
     * @param function2 is a {@link Function} to be passed to
     *                  {@link Comparator#thenComparing(Function)}.
     */
    @SuppressWarnings("unchecked")
    public PlanSortSpecification(final Function<? super Plan,
            ? extends Comparable> function1,
                                 final Function<? super Plan,
                                         ? extends Comparable> function2) {
        comparator = Comparator.comparing(function1).thenComparing(function2);
    }

    /**
     * @param plans {@code List} to be sorted.
     */
    @SuppressWarnings("unchecked")
    public void sort(final List<Plan> plans) {
        plans.sort(comparator);
    }
}
