package by.koshko.task01.service.specification;

public interface FindBySpecification<T> extends Specification<T> {
    boolean isSatisfiedBy(T t);
}
