package by.koshko.task01.service.specification;

import java.util.List;

public interface SortBySpecification<T> extends Specification {
    /**
     * Sorts given {@code List} with objects.
     * @param t {@code List} to be sorted.
     */
    void sort(List<T> t);
}
