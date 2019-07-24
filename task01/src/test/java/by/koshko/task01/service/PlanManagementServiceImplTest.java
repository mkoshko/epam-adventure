package by.koshko.task01.service;

import by.koshko.task01.dao.factory.DaoFactory;
import by.koshko.task01.dao.repository.PlanRepository;
import by.koshko.task01.service.exception.ServiceException;
import by.koshko.task01.service.factory.ServiceFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PlanManagementServiceImplTest {

    PlanRepository repository = DaoFactory.getDaoFactory().getPlanRepository();

    @BeforeTest
    public void init() throws ServiceException {
        ServiceFactory.getInstance().getCreatorService()
                .create("data/plans.txt");
    }

    @Test
    public void testOnUpdate() {
        PlanManagementService service = ServiceFactory.getInstance()
                .getPlanManagementService();
        assertEquals(0, service.getTotalNumberOfCustomers());
        repository.fetchAll().forEach(plan -> plan.setNumberOfUsers(10));
        assertEquals(service.getTotalNumberOfCustomers(),
                repository.fetchAll().size() * 10);

    }
}