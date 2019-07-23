package by.koshko.task01.service.factory;

import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.exception.PlanFactoryException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class PlanFactoryTest {

    private String s1 = "BASIC,15151,PlanS,1.34,1.04,15,0.3,0.5,3,200,24";
    private String s2 = "BASIC,23513484,PlanA,1.12,1.54,123,0.2,0.4,3,3535,35";
    private String s3 = "BASIC,32184,PlanDF,1.53,1.325,1234,4.6,0.5,3,2341,423";
    private String s4 = "INTERNET,1,PlanAD,1.23,1.634,234,0.51,0.3,45,200";
    private String s5 = "INTERNET,2,PlanAVAS,1.12,1.245,3,0.52,0.3,58,500";
    private String s6 = "INTERNET,3,PlanCasd,1.3456,1.12,4,0.59,0.3,150,8001";
    private String s7 = "SOCIAL,4,PlanV,1,1.1345,2,0.56,0.3,12.783,78273,70825,12";
    private String s8 = "SOCIAL,5,PlanX,1.11,23.7,2,0.56,0.3,4.785,5472,4527,1235";
    private String s9 = "SOCIAL,6,PlanASD,2.52,1.45,2,0.56,0.3,2.782,78273,5045,7852";
    private List<String> params;

    @BeforeTest
    private void initList() {
        params = Arrays.asList(s1,s2,s3,s4,s5,s6,s7,s8,s9);
    }

    @DataProvider(name = "params")
    private Object[][] createParams() {
        return new Object[][] {
                {s1},{s2},{s3},{s4},{s5},{s6},{s7},{s8},{s9}
        };
    }

    @Test(dataProvider = "params")
    public void createTest0(String param) throws PlanFactoryException {
        Plan plan = ServiceFactory.getInstance().getPlanFactory().create(param);
        assertNotNull(plan);
    }

    @Test
    public void createTest1() throws PlanFactoryException {
        List<Plan> plans = ServiceFactory.getInstance().getPlanFactory().
                create(params);
        plans.forEach(Assert::assertNotNull);
        assertEquals(plans.size(), 9);
    }

}