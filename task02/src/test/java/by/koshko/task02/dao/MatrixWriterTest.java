package by.koshko.task02.dao;

import by.koshko.task02.entity.Matrix;
import by.koshko.task02.exception.DaoException;
import by.koshko.task02.exception.ServiceException;
import by.koshko.task02.service.MatrixCreatorService;
import by.koshko.task02.service.MatrixCreatorServiceImpl;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Positive and negative test for {@code MatrixWriter} class.
 */
public class MatrixWriterTest {

    /**
     * Path to file with values for matrix creation.
     */
    private String file = "src/test/resources/data/testMatrix2";
    /**
     * Expected {@code Matrix}.
     */
    private Matrix expected;
    /**
     * Service for reading matrix from file.
     */
    private MatrixCreatorService creator = new MatrixCreatorServiceImpl();

    /**
     * Pretest initializations.
     *
     * @throws ServiceException impossible to get there.
     */
    @BeforeTest
    private void init() throws ServiceException {
        expected = creator.createRandomMatrix(10, 10, 1, 10);
    }

    /**
     * Positive test for writing matrix to a file.
     *
     * @throws DaoException     if matrix cannot be written to a file.
     * @throws ServiceException if some exception
     *                          occurs in {@code MatrixCreatorService}.
     */
    @Test
    public void testWrite() throws DaoException, ServiceException {
        MatrixWriter writer = new MatrixWriter(file);
        writer.write(expected);
        var actual = creator.createFromFile(file);
        assertEquals(expected, actual);
    }

    /**
     * Negative test, when file cannot be written to a specified path.
     *
     * @throws DaoException if matrix cannot be written to a specified path.
     */
    @Test(expectedExceptions = DaoException.class)
    public void testWriteFail() throws DaoException {
        MatrixWriter writer = new MatrixWriter("file/not/found");
    }

    /**
     * Negative test when path argument is null.
     *
     * @throws DaoException if matrix cannot be written to a specified path.
     */
    @Test(expectedExceptions = DaoException.class)
    public void testWriteFail2() throws DaoException {
        MatrixWriter writer = new MatrixWriter(null);
    }
}