package by.koshko.task01.service.factory;

import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.exception.PlanFactoryException;

import java.util.List;

public interface PlanFactory {
    /**
     * Creates single instance using given parameters.
     *
     * @param params Parameters to be used for instance creation.
     * @return Instance of an created object.
     * @throws PlanFactoryException when object is not created.
     */
    Plan create(String params) throws PlanFactoryException;

    /**
     * Creates as much instances as much parameters contains in given
     * {@code List}.
     *
     * @param params {@code List} contains strings with parameters for
     *               object creation.
     * @return {@code List} with all created objects.
     * @throws PlanFactoryException when objects is not created because of
     *                              any exception happened.
     */
    List<Plan> create(List<String> params) throws PlanFactoryException;
}
