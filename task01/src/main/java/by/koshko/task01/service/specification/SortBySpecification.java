package by.koshko.task01.service.specification;

import java.util.List;

public interface SortBySpecification<T> extends Specification<T> {
    void sort(List<T> t);
}
