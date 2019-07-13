package by.koshko.task01.service.specification;

public class NotSpecification<T> extends AbstractFindBySpecification<T> {

    private FindBySpecification<T> specification;

    public NotSpecification(final FindBySpecification<T> spec) {
        specification = spec;
    }

    @Override
    public boolean isSatisfiedBy(final T t) {
        return !specification.isSatisfiedBy(t);
    }
}
