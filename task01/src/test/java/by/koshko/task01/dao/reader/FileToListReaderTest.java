package by.koshko.task01.dao.reader;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

public class FileToListReaderTest {

    private static final String s0 = "INTERNET, Compadre Plus, 24.5, 12.54, 25.5, 12.5, 500";
    private static final String s1 = "SOCIAL, Babushka Plan, 24.5, 12.54, 65.7, 545.4, 100, 200, 500";
    private static final String s2 = "BOMOdsf, __ASD, 25.1, 35.5, 124.53";
    private static final String s3 = "COMMON, Bum Plan, 25.1, 35.5, asd";
    private static final String path = "info/plans.txt";
    List<String> expected;
//    FileToListReader reader = new FileToListReader(path);

    @BeforeTest(description = "list initialization")
    public void beforeTestRead() {
        expected = Arrays.asList(s0, s1, s2, s3);
    }

    @Test
    public void testRead() {

    }
}