package service;

import entity.Matrix;
import exception.MatrixException;
import exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code MatrixGenerator} class contains methods for work with
 * {code Matrix} objects.
 */
public class MatrixGeneratorImpl implements MatrixGenerator {
    /**
     * Logger.
     */
    private static Logger log = LogManager.getLogger(MatrixGeneratorImpl.class);

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
        int m = matrix.getVerticalSize();
        int n = matrix.getHorizontalSize();
        log.info("Filling the matrix " + matrix);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = (int) (Math.random() * (max - min) + min);
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
}
