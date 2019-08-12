package by.koshko.task02.service;

import by.koshko.task02.entity.Matrix;
import by.koshko.task02.exception.MatrixException;
import by.koshko.task02.exception.ServiceException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;

/**
 * Positive tests for {@link MatrixGenerator#fillRandom(Matrix, int, int)}
 * method.
 */
public class MatrixGeneratorImplTest {
    /**
     * Matrix to be filled with random values.
     */
    private Matrix matrix;
    /**
     * Service for generate random values and fill matrix with them.
     */
    private MatrixGenerator generator = MatrixGeneratorImpl.access();

    /**
     * Perform initialization operations before tests.
     *
     * @throws MatrixException  if matrix cannot be created.
     * @throws ServiceException if some errors occurs while filling matrix.
     */
    @BeforeTest
    public void init() throws MatrixException, ServiceException {
        final var m = 10;
        final var min = 1;
        final var max = 10;
        matrix = new Matrix(m, m);
        generator.fillRandom(matrix, min, max);
    }

    /**
     * Checks if matrix elements filled with positive numbers greater then 0.
     */
    @Test
    public void testFillRandom() {
        var zero = false;
        for (int i = 0; i < matrix.getVerticalSize(); i++) {
            for (int k = 0; k < matrix.getHorizontalSize(); k++) {
                zero = matrix.getElemFast(i, k) == 0;
            }
        }
        assertFalse(zero);
    }
}
