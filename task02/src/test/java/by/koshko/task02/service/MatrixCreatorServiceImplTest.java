package by.koshko.task02.service;

import by.koshko.task02.entity.Matrix;
import by.koshko.task02.exception.MatrixException;
import by.koshko.task02.exception.ServiceException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Tests with positive and negative scenarios for {@code MatrixCreatorService}.
 */
public class MatrixCreatorServiceImplTest {

    /**
     * Path to file with values for matrix creation.
     */
    private String file = "src/test/resources/data/testMatrix";
    /**
     * {@code Matrix} used like expected matrix for comparison with actual.
     */
    private Matrix expectedMatrix;
    /**
     * Testing service.
     */
    private MatrixCreatorService mcs;
    /**
     * Expected matrix.
     */
    private int[][] expected = new int[][]
            {
                    {5, 8, 7, 3, 9, 7, 1, 1, 2, 8},
                    {8, 5, 4, 3, 3, 2, 9, 6, 8, 6},
                    {3, 4, 6, 9, 5, 6, 4, 7, 9, 8},
                    {2, 6, 2, 6, 7, 2, 2, 7, 3, 7},
                    {8, 8, 4, 7, 3, 6, 4, 1, 9, 3},
                    {9, 6, 2, 9, 4, 2, 6, 1, 5, 2},
                    {4, 6, 5, 3, 5, 5, 2, 9, 1, 4},
                    {7, 6, 1, 2, 2, 9, 6, 8, 1, 2},
                    {3, 6, 3, 3, 6, 9, 1, 5, 7, 4},
                    {8, 7, 6, 5, 5, 4, 1, 2, 9, 7}
            };

    /**
     * Initialize {@code Matrix} and {@code MatrixCreatorService} objects.
     *
     * @throws MatrixException can be ignored.
     */
    @BeforeTest
    private void initMatrix() throws MatrixException {
        expectedMatrix = new Matrix(expected);
        mcs = new MatrixCreatorServiceImpl();
    }

    /**
     * Test matrix creation from file.
     *
     * @throws ServiceException if some errors occurs while reading values.
     */
    @Test
    public void testCreateFromFile() throws ServiceException {
        var actualMatrix = mcs.createFromFile(file);
        assertEquals(expectedMatrix, actualMatrix);
    }

    /**
     * Test creation from file behavior when file does not exist.
     * @throws ServiceException if specified file does not exist.
     */
    @Test(expectedExceptions = ServiceException.class)
    public void testCreateFromFileFail() throws ServiceException {
        mcs.createFromFile("file/not/found");
    }
}
