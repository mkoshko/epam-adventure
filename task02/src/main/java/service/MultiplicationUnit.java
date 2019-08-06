package service;

import entity.Matrix;

public class MultiplicationUnit implements Runnable {
    private Matrix m1;
    private Matrix m2;
    private Matrix result;
    private int start;
    private int rows;

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
