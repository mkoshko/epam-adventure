package by.koshko.task01.service;

import by.koshko.task01.dao.factory.DaoFactory;
import by.koshko.task01.dao.reader.FileToListReader;
import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.exception.PlanFactoryException;
import by.koshko.task01.service.exception.ServiceException;
import by.koshko.task01.service.factory.PlanFactory;
import by.koshko.task01.service.factory.ServiceFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class PlanCreatorServiceImpl implements PlanCreatorService {

    /**
     * Creates {@code Plan} objects from a file.
     * @param path Path to file with parameters.
     * @throws ServiceException if an error occurs during creation process.
     */
    public void create(final String path) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        PlanFactory planFactory = serviceFactory.getPlanFactory();
        List<String> data;
        List<Plan> plans;
        try {
            FileToListReader reader = new FileToListReader(path);
            data = reader.readAllLines();
        } catch (FileNotFoundException e) {
            throw new ServiceException("File not found at \"" + path + "\"");
        } catch (IOException e) {
            throw new ServiceException("Can't read from \"" + path + "\"");
        }
        try {
            plans = planFactory.create(data);
        } catch (PlanFactoryException e) {
            throw new ServiceException("Can't create new plan. "
                                        + e.getMessage());
        }
        daoFactory.getPlanRepository().add(plans);
    }
}
