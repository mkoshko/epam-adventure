package service;

import dao.FileReader;
import entity.Matrix;
import exception.DaoException;
import exception.MatrixException;
import exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MatrixCreatorServiceImpl implements MatrixCreatorService {

    /**
     * Logger.
     */
    private static Logger log
            = LogManager.getLogger(MatrixCreatorServiceImpl.class);
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
    public Matrix createRandomMatrix(final int m, final int n,
                                     final int min, final int max)
            throws ServiceException {
        if (!(m > 0 && n > 0 && max > min)) {
            throw new ServiceException("Invalid parameters");
        }
        Matrix matrix;
        MatrixGenerator generator = MatrixGeneratorImpl.access();
        try {
            matrix = new Matrix(m, n);
            generator.fillRandom(matrix, min, max);
        } catch (MatrixException e) {
            throw new ServiceException(e.getMessage());
        }
        return matrix;
    }

    /**
     * Creates {@code Matrix} and fills it with values obtained from specified
     * file.
     *
     * @param file the file from which the values are obtained
     * @return {@code Matrix} object.
     * @throws ServiceException if some errors occurs while matrix creation.
     */
    public Matrix createFromFile(final String file) throws ServiceException {
        FileReader reader = new FileReader();
        Matrix matrix;
        try {
            matrix = new Matrix(MatrixDataParser.parse(reader.read(file)));
        } catch (DaoException | MatrixException e) {
            throw new ServiceException(e.getMessage());
        }
        return matrix;
    }

}
