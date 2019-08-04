package entity;

import exception.MatrixException;

public class Matrix {

    private int[][] matrix = null;

    public Matrix(final int m, final int n) throws MatrixException {
        if (m > 0 && n > 0) {
            matrix = new int[n][m];
        } else {
            throw new MatrixException("Invalid matrix size");
        }
    }
    public Matrix(final int[][] newMatrix) throws MatrixException {
        if (newMatrix != null
                && newMatrix.length > 0
                && newMatrix[0].length > 0) {
            matrix = newMatrix;
        } else {
            throw new MatrixException("Invalid matrix size");
        }
    }

    public int getVerticalSize() {
        return matrix.length;
    }

    public int getHorizontalSize() {
        return matrix[0].length;
    }

    public int getElement(final int i, final int j) throws MatrixException {
        check(i, j);
        return matrix[i][j];
    }

    public int getElemFast(final int i, final int j) {
        return matrix[i][j];
    }

    public void setElement(final int i, final int j, final int value)
            throws MatrixException {
        check(i, j);
        matrix[i][j] = value;
    }

    public void setElemFast(final int i, final int j, final int value) {
        matrix[i][j] = value;
    }

    private void check(final int i, final int j) throws MatrixException {
        if (!((i >= 0 && i <= matrix.length)
                && (j >= 0 && j <= matrix[0].length))) {
            throw new MatrixException("Index is out of range");
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int[] row : matrix) {
            for (int i : row) {
                builder.append(i).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
