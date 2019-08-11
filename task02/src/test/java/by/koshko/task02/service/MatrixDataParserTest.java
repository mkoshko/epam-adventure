package by.koshko.task02.service;

import by.koshko.task02.exception.ServiceException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class MatrixDataParserTest {
    private List<String> correct = new ArrayList<>();
    private List<String> incorrect = new ArrayList<>();
    private int[][] expected = {
            {1, 2, 3, 4},
            {1, 2, 3, 4},
            {1, 2, 3, 4},
            {1, 2, 3, 4},
    };


    @BeforeTest
    private void init() {
        correct.add("1 2 3 4");
        correct.add("1 2 3 4");
        correct.add("1 2 3 4");
        correct.add("1 2 3 4");

        incorrect.add("f 2 3 4");
        incorrect.add("1 2 3 4");
        incorrect.add("1 2 f 4");
        incorrect.add("f 2 3 4");
    }

    @Test
    public void testParse() throws ServiceException {
        var arr = MatrixDataParser.parse(correct);
        assertTrue(Arrays.deepEquals(arr, expected));
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testParseFail() throws ServiceException {
        var arr = MatrixDataParser.parse(incorrect);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testParseFail2() throws ServiceException {
        var arr = MatrixDataParser.parse(null);
    }
}