package by.koshko.task01.service.factory;

import by.koshko.task01.service.exception.PlanFactoryException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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

    @Test(expectedExceptions = PlanFactoryException.class)
    public void testCreate() throws PlanFactoryException {
        PlanFactory.getInstance().create(list);
    }
}