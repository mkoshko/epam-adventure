package service;

import entity.Matrix;
import exception.ServiceException;

public interface MatrixGenerator {
    /**
     * Fills matrix with random numbers in specified range.
     *
     * @param matrix {@code Matrix} to be filled.
     * @param min    range(inclusive).
     * @param max    range(exclusive).
     * @throws ServiceException if some parameters are invalid.
     */
    void fillRandom(Matrix matrix, int min, int max) throws ServiceException;

}
