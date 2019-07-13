package by.koshko.task01.service.specification;

import java.util.List;

public interface SortBySpecification<T> extends Specification<T> {
    List<T> sort(List<T> ts);
}
