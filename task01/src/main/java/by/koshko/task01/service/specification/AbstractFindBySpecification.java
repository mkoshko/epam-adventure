package by.koshko.task01.service.specification;

public abstract class AbstractFindBySpecification<T> implements FindBySpecification<T> {

    @Override
    public abstract boolean isSatisfiedBy(T t);

    public AbstractFindBySpecification<T> and(final FindBySpecification<T> spec) {
        return new AndSpecification<T>(this, spec);
    }

    public AbstractFindBySpecification<T> or(final FindBySpecification<T> spec) {
        return new OrSpecification<T>(this, spec);
    }

    public AbstractFindBySpecification<T> not() {
        return new NotSpecification<T>(this);
    }
}
