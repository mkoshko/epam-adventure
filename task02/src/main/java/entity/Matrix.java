package entity;

import exception.MatrixException;

public class Matrix {

    /**
     * Array represents a matrix.
     */
    private int[][] matrix = null;

    /**
     * Creates new matrix with given size.
     *
     * @param m horizontal matrix size.
     * @param n vertical matrix size.
     * @throws MatrixException if some parameter less then 1.
     */
    public Matrix(final int m, final int n) throws MatrixException {
        if (m > 0 && n > 0) {
            matrix = new int[n][m];
        } else {
            throw new MatrixException("Invalid matrix size");
        }
    }

    /**
     * Creates a matrix from array.
     *
     * @param newMatrix array which used for matrix creation.
     * @throws MatrixException if some parameters are invalid.
     */
    public Matrix(final int[][] newMatrix) throws MatrixException {
        if (newMatrix != null
                && newMatrix.length > 0
                && newMatrix[0].length > 0) {
            matrix = newMatrix;
        } else {
            throw new MatrixException("Invalid matrix size");
        }
    }

    /**
     * Returns vertical matrix size.
     *
     * @return vertical matrix size.
     */
    public int getVerticalSize() {
        return matrix.length;
    }

    /**
     * Returns horizontal matrix size.
     *
     * @return horizontal matrix size.
     */
    public int getHorizontalSize() {
        return matrix[0].length;
    }

    /**
     * Returns element from a specified position. Also checks if indexes is in a
     * valid range.
     *
     * @param i vertical index.
     * @param j horizontal index.
     * @return integer from specified position.
     * @throws MatrixException if some index out of range.
     */
    public int getElement(final int i, final int j) throws MatrixException {
        check(i, j);
        return matrix[i][j];
    }

    /**
     * Returns element from a specified position. No check for out of range, so
     * NullPointerException can be thrown.
     *
     * @param i vertical index.
     * @param j horizontal index.
     * @return integer from specified position.
     */
    public int getElemFast(final int i, final int j) {
        return matrix[i][j];
    }

    /**
     * Sets the element to a specified position. Also checks if indexes is in a
     * valid range.
     *
     * @param i     vertical index.
     * @param j     horizontal index.
     * @param value value to be set to specified position.
     * @throws MatrixException if some index out of range.
     */
    public void setElement(final int i, final int j, final int value)
            throws MatrixException {
        check(i, j);
        matrix[i][j] = value;
    }

    /**
     * Sets the element to a specified position. No check for out of range, so
     * NullPointerException can be thrown.
     *
     * @param i     vertical index.
     * @param j     horizontal index.
     * @param value value to be set to specified position.
     */
    public void setElemFast(final int i, final int j, final int value) {
        matrix[i][j] = value;
    }

    private void check(final int i, final int j) throws MatrixException {
        if (!((i >= 0 && i <= matrix.length)
                && (j >= 0 && j <= matrix[0].length))) {
            throw new MatrixException("Index is out of range");
        }
    }

    /**
     * Returns string representation of a matrix.
     *
     * @return string representation of a matrix.
     */
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
