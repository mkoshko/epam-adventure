package by.koshko.task02.service;

import by.koshko.task02.exception.ServiceException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Positive and negative test for {@code MatrixDataParser} service.
 */
public class MatrixDataParserTest {
    /**
     * List with correct values for parsing.
     */
    private List<String> correct = new ArrayList<>();
    /**
     * List with correct values for parsing.
     */
    private List<String> correct2 = new ArrayList<>();
    /**
     * List with incorrect values for parsing.
     */
    private List<String> incorrect = new ArrayList<>();
    /**
     * Expected array after parsing.
     */
    private int[][] expected = {
            {1, 2, 3, 4},
            {1, 2, 3, 4},
            {1, 2, 3, 4},
            {1, 2, 3, 4},
    };
    /**
     * Expected array after parsing.
     */
    private int[][] expected2 = {
            {1}
    };

    /**
     * Pretest initializations.
     */
    @BeforeTest
    private void init() {
        correct.add("1 2 3 4");
        correct.add("1 2 3 4");
        correct.add("1 2 3 4");
        correct.add("1 2 3 4");
        correct2.add("1");
        incorrect.add("1 2 3 4");
        incorrect.add("1 2 3 f");
        incorrect.add("1 2 3 4");
        incorrect.add("1 2 3 4");
    }

    /**
     * Test for positive scenario. Parsing a string with correct values.
     *
     * @throws ServiceException if string contains incorrect values.
     */
    @Test
    public void testParse() throws ServiceException {
        var arr = MatrixDataParser.parse(correct);
        assertTrue(Arrays.deepEquals(arr, expected));
    }
    /**
     * Test for positive scenario. Parsing a list with single value.
     *
     * @throws ServiceException if string contains incorrect values.
     */
    @Test
    public void testParse1() throws ServiceException {
        var arr = MatrixDataParser.parse(correct2);
        assertTrue(Arrays.deepEquals(arr, expected2));
    }
    /**
     * Test for negative scenario. Parsing an empty {@code List}.
     *
     * @throws ServiceException if string contains incorrect values.
     */
    @Test(expectedExceptions = ServiceException.class)
    public void testParseFail() throws ServiceException {
        var arr = MatrixDataParser.parse(new ArrayList<>());
    }

    /**
     * Test for negative scenario. Parsing a string with incorrect values.
     *
     * @throws ServiceException if string contains incorrect values.
     */
    @Test(expectedExceptions = ServiceException.class)
    public void testParseFail2() throws ServiceException {
        var arr = MatrixDataParser.parse(incorrect);
    }

    /**
     * Test for negative scenario. Parsing a string which is {@code null}.
     *
     * @throws ServiceException if string contains incorrect values.
     */
    @Test(expectedExceptions = ServiceException.class)
    public void testParseFail3() throws ServiceException {
        var arr = MatrixDataParser.parse(null);
    }
}
