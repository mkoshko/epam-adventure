package by.koshko.task01.service.specification;

public abstract class AbstractFindBySpecification<T>
        implements FindBySpecification<T> {

    @Override
    public abstract boolean isSatisfiedBy(T t);

    /**
     * Creates {@code AndSpecification} and puts new {@code Specification}
     * and the one which invokes this method into it.
     * @param spec new {@code Specification}.
     * @return {@code AndSpecification}.
     */
    public AbstractFindBySpecification<T> and(final FindBySpecification<T> spec) {
        return new AndSpecification<T>(this, spec);
    }

    /**
     * Creates {@code OrSpecification} and puts new {@code Specification}
     * and the one which invokes this method into it.
     * @param spec new {@code Specification}.
     * @return {@code OrSpecification}.
     */
    public AbstractFindBySpecification<T> or(final FindBySpecification<T> spec) {
        return new OrSpecification<T>(this, spec);
    }

    /**
     * Creates new {code NotSpecification} and puts {@code Specifications}
     * which invokes this method into it.
     * @return {@code NotSpecification}.
     */
    public AbstractFindBySpecification<T> not() {
        return new NotSpecification<T>(this);
    }
}
