package by.koshko.task02.service;

import by.koshko.task02.entity.Matrix;
import by.koshko.task02.exception.MatrixException;
import by.koshko.task02.exception.ServiceException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Positive and negative tests for matrix multiplication operation.
 */
public class MatrixMultiplicationTest {
    /**
     * First matrix.
     */
    private Matrix m1;
    /**
     * Second matrix.
     */
    private Matrix m2;
    /**
     * Third matrix.
     */
    private Matrix m3;
    /**
     * Result matrix.
     */
    private Matrix res;
    /**
     * Expected matrix after multiplication.
     */
    private Matrix exp;

    /**
     * Init matrix.
     * @throws MatrixException if matrix not created by any reason.
     */
    @BeforeTest
    public void init() throws MatrixException {
        m1 = new Matrix(new int[][]{
                {4, 7, 6, 9},
                {3, 7, 9, 6},
                {8, 6, 8, 8},
                {6, 8, 5, 7}
        });
        m2 = new Matrix(new int[][]{
                {5, 4, 2, 4},
                {6, 8, 3, 2},
                {4, 8, 5, 1},
                {2, 3, 3, 8}
        });
        m3 = new Matrix(new int[][]{
                {5, 4, 2, 4},
                {6, 8, 3, 2},
                {4, 8, 5, 1}
        });
        exp = new Matrix(new int[][]{
                {104, 147, 86, 108},
                {105, 158, 90, 83},
                {124, 168, 98, 116},
                {112, 149, 82, 101}
        });
    }

    /**
     * Positive test for matrix multiplication.
     * @throws ServiceException if matrices cannot be multiplied.
     */
    @Test
    public void testMultiply() throws ServiceException {
        MatrixMultiplication f = new MatrixMultiplication(m1, m2);
        res = f.multiply();
        assertEquals(res, exp);
    }

    /**
     * Negative test for matrix multiplication.
     *
     * @throws ServiceException if matrices cannot be multiplied.
     */
    @Test(expectedExceptions = ServiceException.class)
    public void testMultiplyFail() throws ServiceException {
        MatrixMultiplication f = new MatrixMultiplication(m1, m3);
        res = f.multiply();
    }
}
