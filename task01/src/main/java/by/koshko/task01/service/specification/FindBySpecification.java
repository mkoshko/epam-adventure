package by.koshko.task01.service.specification;

public interface FindBySpecification<T> extends Specification {
    /**
     * Checks, whether object is meeting expected conditions.
     * @param t object to be checked.
     * @return {@code true} if object meets the conditions.
     */
    boolean isSatisfiedBy(T t);
}
