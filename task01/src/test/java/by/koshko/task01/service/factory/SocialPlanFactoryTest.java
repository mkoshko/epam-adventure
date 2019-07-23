package by.koshko.task01.service.factory;

import by.koshko.task01.entity.SocialPlan;
import by.koshko.task01.service.exception.PlanFactoryException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class SocialPlanFactoryTest {

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
    private String fee = "10.5";
    private double feeD = 10.5;
    private String mwn = "100";
    private int mwnI = 100;
    private String mon = "150";
    private int monI = 150;
    private String fsms = "150";
    private int fsmsI = 150;
    private List<String> paramsCorrect;
    private List<String> paramsIncorrect;

    @BeforeTest
    public void init() {
        paramsCorrect = Arrays.asList("SOCIAL", id, name, cwn, con, cabr,
                csms, cmb, fee, mwn, mon, fsms);
        paramsIncorrect = Arrays.asList("SOCIAL", id, name, "cwn", con, cabr,
                csms, cmb, fee, mwn, mon, fsms);
    }

    @Test
    public void testCreate() throws PlanFactoryException {
        SocialPlan plan = new SocialPlanFactory().create(paramsCorrect);
        assertEquals(plan.getId(), idl);
        assertEquals(plan.getPlanName(), name);
        assertEquals(plan.getOutgoingWithinNetwork(), cwnD);
        assertEquals(plan.getOutgoingOtherNetwork(), conD);
        assertEquals(plan.getOutgoingAbroad(), cabrD);
        assertEquals(plan.getSmsCost(), csmsD);
        assertEquals(plan.getMegabyteCost(), cmbD);
        assertEquals(plan.getMegabyteCost(), cmbD);
        assertEquals(plan.getSubscriptionFee(), feeD);
        assertEquals(plan.getMinutesInNetwork(), mwnI);
        assertEquals(plan.getMinutesOtherNetwork(), monI);
        assertEquals(plan.getFreeSms(), fsmsI);
    }

    @Test(expectedExceptions = PlanFactoryException.class)
    public void testCreateFail() throws PlanFactoryException {
        SocialPlan plan = new SocialPlanFactory().create(paramsIncorrect);
    }
}