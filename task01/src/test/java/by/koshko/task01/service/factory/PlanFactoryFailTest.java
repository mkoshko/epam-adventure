package by.koshko.task01.service.factory;

import by.koshko.task01.service.exception.PlanFactoryException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class PlanFactoryFailTest {

    //incorrect
    private String p1  = "BA_IC,1,Plan1,0.2,0.4,1,0.04,5,100,50";
    private String p2  = "BASIC,1,Plan1,D,0.4,1,0.04,5,100,50";
    private String p3  = "BASIC,1,Plan1,0.2,0.4,1,0.04,5,10.45,50";
    private String p4  = "BASIC,1,Plan1,0.2,0.4,1,0.04,5,100,50,24,123,21";
    private String p5  = "INTE_NET,3,PlanC,1.34,1.23,3,0.56,0.3,15,200";
    private String p6  = "INTERNET,3,PlanC,1.34,S,3,0.56,0.3,15,200";
    private String p7  = "INTERNET,3,_$A$W*^&%,1.34,1.23,3,0.56,0.3,15,200";
    private String p8  = "INTERNET,3,PlanC,1.34,1.23,3,0.56,0.3";
    private String p9  = "SO_IAL,0,PlanS,1.87,1.1345,2,0.56,0.3,5.5,200,100,50";
    private String p10 = "SOCIAL,0,PlanS,1.87,1.1345,2,0.56,0.3,  ";
    private String p11 = "SOCIAL,0,PlanS,1.87,1.1345,2,0.56,0.3,5.5,200,100.584,50";
    private String p12 = "SOCIAL,0,PlanS,1.87,1.1345,2,0.56,0.3,5.5,200,100,50,123,23.3";
    private List<String> params;

    @BeforeTest
    private void initList() {
        params = Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12);
    }

    @DataProvider(name = "incorrect")
    public Object[][] createIncorrectParams() {
        return new Object[][] {
                {p1},{p2},{p3},{p4},{p5},{p6},{p7},{p8},{p9},{p10},{p11},{p12},
        };
    }

    @Test(dataProvider = "incorrect",
            expectedExceptions = PlanFactoryException.class)
    public void failTestCreate(String params) throws PlanFactoryException {
        PlanFactory factory = ServiceFactory.getInstance().getPlanFactory();
        factory.create(params);
    }

    @Test(expectedExceptions = PlanFactoryException.class)
    public void failTestCreate1() throws PlanFactoryException {
        PlanFactory factory = ServiceFactory.getInstance().getPlanFactory();
        factory.create(params);
    }
}