package by.koshko.task01.service.specification;

import static by.koshko.task01.service.specification.FindSpecificationBuilder.*;

import by.koshko.task01.dao.factory.DaoFactory;
import by.koshko.task01.dao.repository.PlanRepository;
import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.exception.PlanFactoryException;
import by.koshko.task01.service.exception.ServiceException;
import by.koshko.task01.service.factory.PlanFactory;
import by.koshko.task01.service.factory.PlanFactoryImpl;
import by.koshko.task01.service.factory.ServiceFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static by.koshko.task01.service.specification.FindSpecificationBuilder.SearchType.*;
import static org.testng.Assert.*;

public class FindSpecificationTest {

    private String plan1 = "BASIC, 1 ,PlanA, 1.21, 1.05, 50, 0.1 ,0.8 ,5,  250, 50";
    private String plan2 = "BASIC, 1 ,PlanA, 1.21, 1.05, 50, 0.1 ,0.8 ,5,  250, 50";
    private String plan3 = "BASIC, 3 ,PlanC, 1.35, 1.12, 31, 0.3 ,0.5 ,25, 180, 80";
    private String plan4 = "BASIC, 5 ,PlanF, 1.48, 1.04, 25, 0.5 ,0.6 ,10, 340, 40";
    private String plan5 = "BASIC, 4 ,PlanE, 1.15, 1.35, 48, 0.8 ,0.2 ,18, 150, 30";
    private String plan6 = "BASIC, 6 ,PlanB, 1.25, 1.21, 45, 0.7 ,0.7 ,16, 50,  60";
    private String plan7 = "BASIC, 2 ,PlanD, 1.58, 1.15, 34, 0.6 ,0.1 ,34, 220, 25";
    PlanRepository repository = DaoFactory.getDaoFactory().getPlanRepository();

    @BeforeTest
    private void init() throws ServiceException, PlanFactoryException {
        List<String> params = Arrays.asList(plan1, plan2, plan3, plan4, plan5, plan6, plan7);
        PlanFactory factory = new PlanFactoryImpl();
        repository.add(factory.create(params));
    }

    @Test
    public void testFindName() throws ServiceException {
        FindSpecificationBuilder builder = new FindSpecificationBuilder();
        builder.compose(FindByName, "PlanA");
        AbstractFindBySpecification s = builder.build();
        List<Plan> result = repository.query(s);
        result.forEach(plan -> assertEquals(plan.getPlanName(), "PlanA"));
    }

    @Test
    public void testFindId() throws ServiceException {
        FindSpecificationBuilder builder = new FindSpecificationBuilder();
        builder.compose(FindByID, "5");
        AbstractFindBySpecification s = builder.build();
        List<Plan> result = repository.query(s);
        result.forEach(plan -> assertEquals(plan.getId(), 5));
    }

    @Test
    public void testFindCostWN() throws ServiceException {
        FindSpecificationBuilder builder = new FindSpecificationBuilder();
        builder.compose(FindByCostWN, "1.48");
        AbstractFindBySpecification s = builder.build();
        List<Plan> result = repository.query(s);
        result.forEach(plan -> assertEquals(plan.getOutgoingWithinNetwork(), 1.48));
    }

    @Test
    public void testFindCostON() throws ServiceException {
        FindSpecificationBuilder builder = new FindSpecificationBuilder();
        builder.compose(FindByCostON, "1.21");
        AbstractFindBySpecification s = builder.build();
        List<Plan> result = repository.query(s);
        result.forEach(plan -> assertEquals(plan.getOutgoingOtherNetwork(), 1.21));
    }

    @Test
    public void testFindCostAbr() throws ServiceException {
        FindSpecificationBuilder builder = new FindSpecificationBuilder();
        builder.compose(FindByCostAbroad, "48");
        AbstractFindBySpecification s = builder.build();
        List<Plan> result = repository.query(s);
        result.forEach(plan -> assertEquals(plan.getOutgoingAbroad(), 48d));
    }

    @Test
    public void testFindCostSms() throws ServiceException {
        FindSpecificationBuilder builder = new FindSpecificationBuilder();
        builder.compose(FindByCostSms, "0.8");
        AbstractFindBySpecification s = builder.build();
        List<Plan> result = repository.query(s);
        result.forEach(plan -> assertEquals(plan.getSmsCost(), 0.8));
    }

    @Test
    public void testFindCostMB() throws ServiceException {
        FindSpecificationBuilder builder = new FindSpecificationBuilder();
        builder.compose(FindByCostMegabyte, "0.8");
        AbstractFindBySpecification s = builder.build();
        List<Plan> result = repository.query(s);
        result.forEach(plan -> assertEquals(plan.getMegabyteCost(), 0.8));
    }

    @Test
    public void testFindBetween() throws ServiceException {
        FindSpecificationBuilder builder = new FindSpecificationBuilder();
        builder.compose(FindByCostWN, "1.15", "1.35");
        AbstractFindBySpecification s = builder.build();
        List<Plan> result = repository.query(s);
        result.forEach(plan -> assertTrue(
                plan.getMegabyteCost() <= 1.35
                        || plan.getMegabyteCost() >= 1.15));
    }
}