package service;

import entity.Matrix;

public class MultiplicationUnit implements Runnable {
    /**
     * First matrix.
     */
    private Matrix m1;
    /**
     * Second matrix.
     */
    private Matrix m2;
    /**
     * Result matrix.
     */
    private Matrix result;
    /**
     * Start position.
     */
    private int start;
    /**
     * Amount of rows needs to be passed.
     */
    private int rows;

    /**
     * Constructor.
     *
     * @param matrix1 first matrix.
     * @param matrix2 second matrix.
     * @param resultMatrix matrix in which result of multiplication
     *                     will be placed.
     * @param startPosition index from where multiplication will begin.
     * @param rowsNumber number of rows to be processed.
     */
    public MultiplicationUnit(final Matrix matrix1,
                              final Matrix matrix2,
                              final Matrix resultMatrix,
                              final int startPosition,
                              final int rowsNumber) {
        m1 = matrix1;
        m2 = matrix2;
        result = resultMatrix;
        start = startPosition;
        rows = rowsNumber;
    }

    /**
     * Method performs multiplication operation.
     */
    @Override
    public void run() {
        int row = (start + (rows - 1));
        int size = m1.getHorizontalSize();
        int size2 = m2.getHorizontalSize();
        for (int i = start; i <= row; i++) {
            for (int j = 0; j < size2; j++) {
                int sum = 0;
                for (int k = 0; k < size; k++) {
                    sum += m1.getElemFast(i, k) * m2.getElemFast(k, j);
                }
                result.setElemFast(i, j, sum);
            }
        }
    }
}
