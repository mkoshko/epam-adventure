package by.koshko.task01.service.specification;

import java.util.HashSet;
import java.util.Set;

public class AndSpecification<T> extends AbstractFindBySpecification<T> {

    private Set<FindBySpecification<T>> set = new HashSet<>();

    public AndSpecification(final FindBySpecification<T> a, final FindBySpecification<T> b) {
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
    public AbstractFindBySpecification<T> and(final FindBySpecification<T> spec) {
        set.add(spec);
        return this;
    }
}
