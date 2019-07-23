package by.koshko.task01.service.factory;

import by.koshko.task01.entity.BasicPlan;
import by.koshko.task01.service.exception.PlanFactoryException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class BasicPlanFactoryTest {

    private String id = "1";
    private long idl = 1;
    private String name = "BasicPlan";
    private String cwn = "0.1";
    private double cwnD = 0.1;
    private String con = "0.2";
    private double conD = 0.2;
    private String cabr = "0.3";
    private double cabrD = 0.3;
    private String csms = "0.4";
    private double csmsD = 0.4;
    private String cmb = "0.5";
    private double cmbD = 0.5;
    private String fn = "10";
    private int fnd = 10;
    private String mtfn = "100";
    private int mtfnI = 100;
    private String smstfn = "50";
    private int smstfnI = 50;
    private List<String> paramsCorrect;
    private List<String> paramsIncorrect;

    @BeforeTest
    public void init() {
        paramsCorrect = Arrays.asList("BASIC", id, name, cwn, con, cabr,
                csms, cmb, fn, mtfn, smstfn);
        paramsIncorrect = Arrays.asList("BASIC", id, name, "cwn", con, cabr,
                csms, cmb, fn,mtfn, smstfn);
    }

    @Test
    public void testCreate() throws PlanFactoryException {
        BasicPlan plan = new BasicPlanFactory().create(paramsCorrect);
        assertEquals(plan.getId(), idl);
        assertEquals(plan.getPlanName(), name);
        assertEquals(plan.getOutgoingWithinNetwork(), cwnD);
        assertEquals(plan.getOutgoingOtherNetwork(), conD);
        assertEquals(plan.getOutgoingAbroad(), cabrD);
        assertEquals(plan.getSmsCost(), csmsD);
        assertEquals(plan.getMegabyteCost(), cmbD);
        assertEquals(plan.getMegabyteCost(), cmbD);
        assertEquals(plan.getFavouriteNumbers(), fnd);
        assertEquals(plan.getMinutesToFavouriteNumber(), mtfnI);
        assertEquals(plan.getSmsToFavouriteNumber(), smstfnI);
    }

    @Test(expectedExceptions = PlanFactoryException.class)
    public void testCreateFail() throws PlanFactoryException {
        BasicPlan plan = new BasicPlanFactory().create(paramsIncorrect);
    }
}