package by.koshko.task01.service.specification;

public class NotSpecification<T> extends AbstractFindBySpecification<T> {

    /**
     * Contains specification which has invoked creation of this one.
     */
    private FindBySpecification<T> specification;

    /**
     * Creates new {@code NotSpecification}.
     * @param spec {@code Specification} which has invoked creation of this one.
     */
    public NotSpecification(final FindBySpecification<T> spec) {
        specification = spec;
    }

    /**
     * Checks, whether object is meeting expected conditions and invert result.
     * @param t object to be checked.
     * @return inverted result of the method {@link #isSatisfiedBy(Object)}
     */
    @Override
    public boolean isSatisfiedBy(final T t) {
        return !specification.isSatisfiedBy(t);
    }
}
