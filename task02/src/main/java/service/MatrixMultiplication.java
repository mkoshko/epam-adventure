package service;

import entity.Matrix;
import exception.MatrixException;
import exception.ServiceException;

public class MatrixMultiplication {
    /**
     * The first matrix over which multiplication operation will be performed.
     */
    private Matrix matrix1;
    /**
     * The second matrix on which multiplication operation will be performed.
     */
    private Matrix matrix2;
    /**
     * Matrix which will contain the result of the multiplication.
     */
    private Matrix result;
    /**
     * Default thread number which well be used unless otherwise specified.
     */
    private int threadNumber = 1;

    /**
     * Constructor.
     * @param m1 first matrix.
     * @param m2 second matrix.
     * @throws ServiceException if some matrix is null.
     */
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

    /**
     * Perform multiplication operation.
     * @return {@code Matrix} object that is the result of matrices
     *         multiplication.
     */
    public Matrix multiply() {
        int rowsLeft = result.getVerticalSize();
        int threadsLeft = threadNumber;
        int startIndex = 0;
        Thread[] threads = new Thread[threadNumber];
        int t = 0;
        for (int i = 0; i < threadNumber; i++) {
            int rows = rowsLeft / threadsLeft;
            threads[t] = new Thread(new MultiplicationUnit(matrix1, matrix2,
                    result, startIndex, rows));
            t++;
            threadsLeft--;
            startIndex += rows;
            rowsLeft -= rows;
        }

        for (Thread th : threads) {
            th.start();
        }
        for (Thread th : threads) {
            try {
                th.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return result;
    }

    /**
     * Sets the {@link #threadNumber} value.
     * @param quantity number of threads to use in multiplication operation.
     *                 Must be equal or greater then 1.
     * @return {@code MatrixMultiplication} object.
     */
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
