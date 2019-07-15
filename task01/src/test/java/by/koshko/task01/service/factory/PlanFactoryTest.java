package by.koshko.task01.service.factory;

import by.koshko.task01.dao.reader.FileToListReader;
import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.exception.InvalidPlanArgumentsException;
import by.koshko.task01.service.exception.PlanFactoryException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlanFactoryTest {

    private static final String s0 = "RRR, 23.2";
    List<String> list;

    @BeforeTest
    public void init() {
        list = new ArrayList<>();
        list.add(s0);
    }

    @Test(expectedExceptions = PlanFactoryException.class, enabled = false)
    public void testCreate() throws PlanFactoryException {
        PlanFactory.getInstance().create(list);
    }

    @Test(expectedExceptions = PlanFactoryException.class)
    public void testCreate1() throws PlanFactoryException, IOException {
        FileToListReader reader = new FileToListReader("src/main/resources/data/plans.txt");
        List<String> params = reader.readAllLines();
        Plan plan = PlanFactory.getInstance().create(params.get(10));
    }
}