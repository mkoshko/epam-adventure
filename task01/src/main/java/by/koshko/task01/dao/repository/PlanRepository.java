package by.koshko.task01.dao.repository;

import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.specification.Specification;

import java.util.List;

public interface PlanRepository {

    /**
     * Adds given {@code Plan} to repository.
     *
     * @param plan {@code Plan} to be added to repository.
     * @return {@code true} if plan is successfully added to repository,
     * {@code false} otherwise.
     */
    boolean add(Plan plan);

    /**
     * Adds all {@code Plan} objects from {@code List} to repository.
     *
     * @param plans {@code Plan}'s to be added to repository.
     * @return {@code true} if plan is successfully added to repository,
     * {@code false} otherwise.
     */
    boolean add(List<? extends Plan> plans);

    /**
     * Removes specified {@code Plan} from repository.
     *
     * @param plan {@code Plan} to be removed from repository.
     * @return {@code true} if specified plan is removed from repository,
     * {@code false} if specified {@code Plan} doesn't exists in the repository,
     * and nothing to remove.
     */
    boolean remove(Plan plan);

    /**
     * Removes all specified plan from repository.
     *
     * @param plans {@code List} with plans to be removed from repository.
     * @return {@code true} if plans was removed.
     */
    boolean remove(List<? extends Plan> plans);

    /**
     * Returns all {@code Plan} objects from repository.
     *
     * @return all {@code Plan} objects from repository.
     */
    List<Plan> fetchAll();

    /**
     * Returns {@code List} with {@code Plan} objects that satisfied to all
     * conditions from given {@code Specification}.
     *
     * @param specification Objects contains conditions that should be met by
     *                      an {@code Plan} objects.
     * @return {@code List} with {@code Plan} objects that satisfied to given
     * {@code Specification}.
     */
    List<Plan> query(Specification specification);
}
