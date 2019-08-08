package service;

import entity.Matrix;
import exception.MatrixException;
import exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

/**
 * The {@code MatrixGenerator} class contains methods for work with
 * {code Matrix} objects.
 */
public final class MatrixGeneratorImpl implements MatrixGenerator {
    /**
     * Logger.
     */
    private static Logger log = LogManager.getLogger(MatrixGeneratorImpl.class);
    /**
     * MatrixGenerator instance.
     */
    private static MatrixGenerator instance;

    private MatrixGeneratorImpl() {
    }

    /**
     * Thread safe method for creating or obtaining instance
     * of the {@code MatrixGenerator}.
     *
     * @return {@code MatrixGenerator} object.
     */
    public static MatrixGenerator access() {
        MatrixGenerator temp;
        if (instance != null) {
            return instance;
        } else {
            synchronized (MatrixGeneratorImpl.class) {
                temp = instance;
                if (temp == null) {
                    instance = new MatrixGeneratorImpl();
                }
            }
        }
        return instance;
    }

    /**
     * Fills matrix with random numbers in specified range.
     *
     * @param matrix {@code Matrix} to be filled.
     * @param min    range(inclusive).
     * @param max    range(exclusive).
     * @throws ServiceException if some parameters are invalid.
     */
    public void fillRandom(final Matrix matrix,
                           final int min,
                           final int max) throws ServiceException {
        if (max < min) {
            throw new ServiceException("Max range value should be greater"
                    + " than min value");
        }
        Random rand = new Random();
        int m = matrix.getVerticalSize();
        int n = matrix.getHorizontalSize();
        log.info("Filling the matrix " + matrix);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = (rand.nextInt(max - min) + min);
                try {
                    log.debug("Fill element" + "[" + i + "]"
                            + "[" + j + "]" + " with " + val + " value");
                    matrix.setElement(i, j, val);
                } catch (MatrixException e) {
                    log.error(e.getMessage());
                    throw new RuntimeException();
                }
            }
        }
    }

    /**
     * Fills main matrix diagonal with zeros.
     *
     * @param matrix {@code Matrix} whose diagonal be filled with zeros.
     * @throws ServiceException if matrix is not square (m != n).
     */
    public void fillMainDiagonalWithZero(final Matrix matrix)
            throws ServiceException {
        if (matrix.getVerticalSize() != matrix.getHorizontalSize()) {
            throw new ServiceException("Matrix is not square");
        }
        final int size = matrix.getHorizontalSize();
        for (int i = 0; i < size; i++) {
            matrix.setElemFast(i, i, 0);
        }
    }
}
