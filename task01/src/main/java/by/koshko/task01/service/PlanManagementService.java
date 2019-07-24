package by.koshko.task01.service;

import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.exception.ServiceException;
import by.koshko.task01.service.specification.Specification;

import java.util.List;

public interface PlanManagementService {

    /**
     * Returns total number of customers.
     *
     * @return total number of customers.
     */
    int getTotalNumberOfCustomers();

    /**
     * Returns all existed {@code Plan} objects.
     *
     * @return {@code List} with {@code Plan} objects.
     * @throws ServiceException if {@code PlanRepository} is {@code null}.
     */
    List<Plan> receiveAllAvailablePlans() throws ServiceException;

    /**
     * Returns {@code List} of {@code Plan} according to the specification.
     *
     * @param specification {@code Specification} using for making selection
     *                      from {@code PlanRepository}.
     * @return {@code List} of {@code Plan}.
     * @throws ServiceException if {@code PlanRepository} is {@code null}.
     */
    List<Plan> queryToPlanRepository(Specification specification)
            throws ServiceException;

    /**
     * Removes all {@code Plan} objects from last query.
     *
     * @return {@code true} if {@code Plan} was removed,
     * {@code false} otherwise.
     * @throws ServiceException If no query was done to repository.
     */
    boolean remove() throws ServiceException;

    /**
     * Removes {@code Plan} with specified index in queried list from
     * repository.
     *
     * @param index Index of a plan in queried list to be removed from
     *              repository.
     * @return {@code true} if {@code Plan} was removed,
     * {@code false} otherwise.
     * @throws ServiceException If no query was done to repository
     *                          or wrong index value.
     */
    boolean remove(int index) throws ServiceException;

    /**
     * Removes {@code Plan} with specified index in queried list from
     * repository.
     *
     * @param leftIndex  low endpoint (inclusive) of the subList
     * @param rightIndex high endpoint (exclusive) of the subList.
     * @return {@code true} if {@code Plan} from specified range was removed,
     * {@code false} otherwise.
     * @throws ServiceException If no query was done to repository
     *                          or wrong indexes values.
     */
    boolean remove(int leftIndex, int rightIndex)
            throws ServiceException;

    /**
     * Writes queried plans to a specified file.
     * @param path to a file.
     * @return {@code true} if report was successfully wrote to a file.
     * @throws ServiceException if some exceptions occurs during
     *                          writing a report.
     */
    boolean report(String path) throws ServiceException;
}
