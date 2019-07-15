package by.koshko.task01.dao.repository;

import by.koshko.task01.dao.reader.FileToListReader;
import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.exception.PlanFactoryException;
import by.koshko.task01.service.factory.PlanFactory;
import by.koshko.task01.service.specification.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

public class PlanRepositoryTest {

    @BeforeTest
    public void init() throws IOException, PlanFactoryException {
        FileToListReader reader = new FileToListReader("src/main/resources/data/plans.txt");
        List<String> params = reader.readAllLines();
        final List<Plan> listValue = PlanFactory.getInstance().create(params);
        for (Plan p : listValue) {
            PlanRepository.accept().add(p);
            System.out.println(p);
        }
        System.out.println("===============================================");
    }
    @Test(enabled = false)
    public void testQuery0() {
        AbstractFindBySpecification<Plan> s =
                new PlanNameSpecification("CompadreX")
                .or(new PlanNameSpecification("CompadreS")).and(new PlanIdSpecification(0));
        List<Plan> list = PlanRepository.accept().query(s);
        for (Plan p : list) {
            System.out.println(p);
        }
    }

    @Test(enabled = false)
    public void testQuery1() {
        AbstractFindBySpecification<Plan> s =
                new PlanDoubleBetweenSpecification(1, 1.5, Plan::getOutgoingWithinNetwork)
                .and(new PlanNameSpecification("CompadreS"));
        List<Plan> list = PlanRepository.accept().query(s);
        for (Plan p : list) {
            System.out.println(p);
        }
    }

    @Test(enabled = true)
    public void testQuery2() {
        PlanSortSpecification s = new PlanSortSpecification(Plan::getPlanName);
        PlanRepository.accept().query(s);
        PlanRepository.accept().getAll().forEach(System.out::println);
    }
}