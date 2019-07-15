package by.koshko.task01.service.specification;

import by.koshko.task01.entity.Plan;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class PlanSortSpecification implements SortBySpecification<Plan> {

    private Comparator c;

    public PlanSortSpecification(final Function<? super Plan, ? extends Comparable> f) {
        c = Comparator.comparing(f);
    }

    public PlanSortSpecification(final Function<? super Plan, ? extends Comparable> f1,
                                 final Function<? super Plan, ? extends Comparable> f2) {
        c = Comparator.comparing(f1).thenComparing(f2);
    }

    @Override
    public void sort(final List<Plan> t) {
        t.sort(c);
    }
}
