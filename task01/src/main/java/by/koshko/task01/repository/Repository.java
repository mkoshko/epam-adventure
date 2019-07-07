package by.koshko.task01.repository;

import java.util.List;

public interface Repository<T> {
    /**
     * Adds object to repository.
     * @param t object to be added to repository.
     * @return true if object is added, false otherwise.
     */
    boolean add(T t);

    /**
     * Removes object from repository.
     * @param planName object to be removed from repository.
     * @return <tt>true</tt> if the repository contained the specified element.
     */
    boolean remove(String planName);

    /**
     * Search object with specified id.
     * @param planName used to search object in repository.
     * @return object if it present in repository or {@code null} if not.
     */
    T findByName(String planName);

    /**
     * Return all objects from repository.
     * @return {@code List<T>} with all objects from repository.
     */
    List<T> getAll();
}
