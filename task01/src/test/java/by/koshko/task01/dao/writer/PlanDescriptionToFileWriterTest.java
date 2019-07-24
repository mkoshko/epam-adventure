package by.koshko.task01.dao.writer;

import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.exception.PlanFactoryException;
import by.koshko.task01.service.factory.ServiceFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static java.nio.file.Files.*;
import static org.testng.Assert.*;

public class PlanDescriptionToFileWriterTest {

    private String p1  = "BASIC,7,PlanS,1.5,1.2,7,0.6,0.5,3,200,50";
    private String p2  = "INTERNET,3,PlanC,1.34,1.23,3,0.56,0.3,15,200";
    private String p3  = "SOCIAL,0,PlanS,1.87,1.1345,2,0.56,0.3,5.5,200,100,50";
    private String p1_expected = "BasicPlan{plan id =7, plan name='PlanS',"
            +" outgoing within network cost=1.5, outgoing other networks cost=1.2,"
            +" outgoing abroad=7.0, sms cost=0.6, megabyte cost=0.5,"
            +" minutes to favourite numbers=200, sms to favourite numbers=50}\n";
    private String p2_expected = "InternetPlan{plan id=3, plan name='PlanC',"
            +" outgoing within network cost=1.34, outgoing other networks"
            +" cost=1.23, outgoing abroad=3.0, sms cost=0.56, megabyte cost=0.3,"
            +" subscription fee=15.0, internet traffic=200}\n";
    private String p3_expected = "SocialPlan{plan id=0, plan name='PlanS',"
            +" outgoing within network cost=1.87, outgoing other networks"
            +" cost=1.1345, outgoing abroad=2.0, sms cost=0.56, megabyte"
            +" cost=0.3, subscription fee=5.5, free minutes within network=200,"
            +" free minutes to other Networks=100, free sms=50}\n";
    private List<Plan> plans;
    private List<String> params;
    private String path = "data/writer_test.txt";

    @BeforeTest
    private void init() throws PlanFactoryException {
        params = Arrays.asList(p1, p2, p3);
        plans = ServiceFactory.getInstance().getPlanFactory().create(params);
    }

    @Test
    public void testWrite() throws IOException {
        PlanDescriptionToFileWriter writer
                = new PlanDescriptionToFileWriter(path);
        writer.write(plans);
        String actual = readString(Paths.get(path));
        assertEquals(actual, p1_expected + p2_expected + p3_expected);
    }

    @Test(expectedExceptions = FileNotFoundException.class)
    public void testWriteNullPath() throws FileNotFoundException {
        PlanDescriptionToFileWriter writer
                = new PlanDescriptionToFileWriter(null);
    }
}