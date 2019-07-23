package by.koshko.task01.service.specification;

import java.util.HashSet;
import java.util.Set;

public final class OrSpecification<T> extends AbstractFindBySpecification<T> {

    /**
     * Contains specifications.
     */
    private Set<FindBySpecification> set = new HashSet<>();

    /**
     * Creates new {@code OrSpecification} and puts given specifications to
     * {@link #set}.
     * @param a {@code Specification} which invokes creation of this
     *          {@code OrSpecification}.
     * @param b New {@code Specification}.
     */
    public OrSpecification(final FindBySpecification<T> a,
                           final FindBySpecification<T> b) {
        super();
        set.add(a);
        set.add(b);
    }

    @Override
    public boolean isSatisfiedBy(final T t) {
        for (FindBySpecification<T> s : set) {
            if (s.isSatisfiedBy(t)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public AbstractFindBySpecification<T> or(
            final FindBySpecification<T> spec) {
        set.add(spec);
        return this;
    }
}
