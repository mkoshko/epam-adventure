package by.koshko.task01.service.specification;

import by.koshko.task01.dao.factory.DaoFactory;
import by.koshko.task01.dao.repository.PlanRepository;
import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.exception.PlanFactoryException;
import by.koshko.task01.service.exception.ServiceException;
import by.koshko.task01.service.factory.PlanFactory;
import by.koshko.task01.service.factory.PlanFactoryImpl;
import by.koshko.task01.service.factory.ServiceFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static by.koshko.task01.service.specification.SortSpecificationBuilder.SortBy.*;

import static org.testng.Assert.*;

public class SortSpecificationTest {

    private String plan1 = "BASIC, 1 ,PlanA, 1.21, 1.05, 50, 0.1 ,0.8 ,5,  250, 50";
    private String plan2 = "BASIC, 1 ,PlanA, 1.14, 1.05, 50, 0.1 ,0.8 ,5,  250, 50";
    private String plan3 = "BASIC, 3 ,PlanC, 1.35, 1.12, 31, 0.3 ,0.5 ,25, 180, 80";
    private String plan4 = "BASIC, 5 ,PlanF, 1.48, 1.04, 25, 0.5 ,0.6 ,10, 340, 40";
    private String plan5 = "BASIC, 4 ,PlanF, 1.15, 1.35, 48, 0.8 ,0.2 ,18, 150, 30";
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
    public void testSortName() throws ServiceException {
        SortSpecificationBuilder builder = new SortSpecificationBuilder();
        PlanSortSpecification s = builder.build(Name);
        List<Plan> sorted = repository.query(s);
        for (int i = 0; i < sorted.size() - 1; i++) {
            assertTrue(
                    sorted.get(i).getPlanName()
                            .compareTo(sorted.get(i+1).getPlanName()) <= 0
            );
        }
    }

    @Test
    public void testSortId() throws ServiceException {
        SortSpecificationBuilder builder = new SortSpecificationBuilder();
        PlanSortSpecification s = builder.build(Id);
        List<Plan> sorted = repository.query(s);
        for (int i = 0; i < sorted.size() - 1; i++) {
            assertTrue(sorted.get(i).getId() <= sorted.get(i+1).getId());
        }
    }

    @Test
    public void testSortCostWN() throws ServiceException {
        SortSpecificationBuilder builder = new SortSpecificationBuilder();
        PlanSortSpecification s = builder.build(CostIN);
        List<Plan> sorted = repository.query(s);
        for (int i = 0; i < sorted.size() - 1; i++) {
            Double v1 = sorted.get(i).getOutgoingWithinNetwork();
            Double v2 = sorted.get(i + 1).getOutgoingWithinNetwork();
            assertTrue(v1.compareTo(v2) <= 0);
        }
    }

    @Test(dependsOnMethods = "testSortName")
    public void testSortDouble() throws ServiceException {
        SortSpecificationBuilder builder = new SortSpecificationBuilder();
        PlanSortSpecification s = builder.build(Name, CostIN);
        List<Plan> sorted = repository.query(s);
        for (int i = 0; i < sorted.size() - 1; i++) {
            String v1 = sorted.get(i).getPlanName();
            String v2 = sorted.get(i + 1).getPlanName();
            Double d1 = sorted.get(i).getOutgoingWithinNetwork();
            Double d2 = sorted.get(i + 1).getOutgoingWithinNetwork();
            if (v1.compareTo(v2) == 0) {
                assertTrue(d1.compareTo(d2) <= 0);
            }
        }
    }
}