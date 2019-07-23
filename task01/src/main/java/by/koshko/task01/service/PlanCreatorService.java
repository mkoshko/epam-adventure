package by.koshko.task01.service;

import by.koshko.task01.service.exception.ServiceException;

public interface PlanCreatorService {

    /**
     * Creates {@code Plan} objects.
     * @param path Path to file with parameters.
     * @throws ServiceException if some {@code Plan} object can't be created.
     */
    void create(String path) throws ServiceException;

}
