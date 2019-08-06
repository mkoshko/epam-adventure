package service;

import entity.Matrix;
import exception.MatrixException;
import exception.ServiceException;

public class MatrixMultiplication {
    private Matrix matrix1;
    private Matrix matrix2;
    private Matrix result;
    private int threadNumber = 1;
    private Thread[] threads;

    public MatrixMultiplication(final Matrix m1, final Matrix m2)
            throws ServiceException {
        if (m1 == null || m2 == null) {
            throw new ServiceException("Matrix is null");
        }
        checkMatrix(m1, m2);
        matrix1 = m1;
        matrix2 = m2;
        createResultMatrix();
    }

    public Matrix multiply() {


        for (Thread th : threads) {
            th.start();
        }
        for (Thread th : threads) {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public MatrixMultiplication useThreads(final int quantity) {
        if (quantity <= 0) {
            threadNumber = 1;
            return this;
        }
        if (quantity > matrix1.getVerticalSize()) {
            threadNumber = matrix1.getVerticalSize();
            return this;
        }
        threadNumber = quantity;
        return this;
    }

    private void checkMatrix(final Matrix m1, final Matrix m2)
            throws ServiceException {
        if (m1.getHorizontalSize() != m2.getVerticalSize()) {
            throw new ServiceException("Matrices cannot be multiplied");
        }
    }

    private void createResultMatrix() throws ServiceException {
        try {
            result = new Matrix(matrix2.getHorizontalSize(),
                    matrix1.getVerticalSize());
        } catch (MatrixException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
