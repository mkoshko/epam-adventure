package by.koshko.task02.service;

import by.koshko.task02.entity.Matrix;
import by.koshko.task02.exception.MatrixException;
import by.koshko.task02.exception.ServiceException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MatrixGeneratorImplTest {
    private Matrix matrix;
    MatrixGenerator generator = MatrixGeneratorImpl.access();

    @BeforeTest
    public void init() throws MatrixException, ServiceException {
        matrix = new Matrix(10, 10);
        generator.fillRandom(matrix, 1, 10);
    }
    @Test
    public void testFillRandom() {
        System.out.println(matrix);
        var zero = false;
        for (int i = 0; i < matrix.getVerticalSize(); i++) {
            for (int k = 0; k < matrix.getHorizontalSize(); k++) {
                zero = matrix.getElemFast(i, k) == 0;
            }
        }
        assertFalse(zero);
    }
}