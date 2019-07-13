package by.koshko.task01.service.specification;

import java.util.HashSet;
import java.util.Set;

public class OrSpecification<T> extends AbstractFindBySpecification<T> {

    private Set<FindBySpecification> set = new HashSet<>();

    public OrSpecification(final FindBySpecification<T> a, final FindBySpecification<T> b) {
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
    public AbstractFindBySpecification<T> or(final FindBySpecification<T> spec) {
        set.add(spec);
        return this;
    }
}
