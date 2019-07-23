package by.koshko.task01.service.specification;

import java.util.HashSet;
import java.util.Set;

public final class AndSpecification<T> extends AbstractFindBySpecification<T> {

    /**
     * Contains specifications.
     */
    private Set<FindBySpecification<T>> set = new HashSet<>();

    /**
     * Creates new {@code AndSpecification} and puts given specifications to
     * {@link #set}.
     * @param a {@code Specification} which invokes creation of this
     *          {@code AndSpecification}.
     * @param b New {@code Specification}.
     */
    public AndSpecification(final FindBySpecification<T> a,
                            final FindBySpecification<T> b) {
        super();
        set.add(a);
        set.add(b);
    }

    @Override
    public boolean isSatisfiedBy(final T t) {
        for (FindBySpecification<T> s : set) {
            if (!s.isSatisfiedBy(t)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public AbstractFindBySpecification<T> and(
            final FindBySpecification<T> spec) {
        set.add(spec);
        return this;
    }
}
