package by.koshko.task02.service;

import by.koshko.task02.entity.Matrix;
import by.koshko.task02.exception.ServiceException;

public interface MatrixCreatorService {
    /**
     * Creates {@code Matrix} and fills it with values obtained from specified
     * file.
     *
     * @param file the file from which the values are obtained
     * @return {@code Matrix} object.
     * @throws ServiceException if some errors occurs while matrix creation.
     */
    Matrix createFromFile(String file) throws ServiceException;

    /**
     * Creates matrix with specified size and fills it with random values
     * in specified range.
     *
     * @param m   vertical matrix size.
     * @param n   horizontal matrix size.
     * @param min range(inclusive).
     * @param max range(exclusive).
     * @return created {@code Matrix} object.
     * @throws ServiceException if some parameters are invalid.
     */
    Matrix createRandomMatrix(int m, int n, int min, int max)
            throws ServiceException;
}
