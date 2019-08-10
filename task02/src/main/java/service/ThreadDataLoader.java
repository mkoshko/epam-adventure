package service;

import dao.FileReader;
import exception.DaoException;
import exception.ServiceException;

import java.util.Arrays;
import java.util.List;

/**
 * Class contains method for loading and validating data from a file.
 */
public final class ThreadDataLoader {
    private ThreadDataLoader() {
    }

    /**
     * Loads parameters for threads creation from specified file.
     *
     * @param file from where data will be loaded.
     * @return list of strings array with arguments for thread creation.
     * @throws ServiceException if data can't be loaded from file or some
     *                          arguments is invalid.
     */
    public static List<String[]> loadData(final String file)
            throws ServiceException {
        FileReader reader = new FileReader();
        List<String> data = null;
        List<String[]> args = null;
        try {
            data = reader.read(file);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        args = ThreadDataParser.parse(data);
        for (String[] args0 : args) {
            if (!ThreadDataValidation.validate(args0)) {
                throw new ServiceException("Invalid arguments "
                        + Arrays.toString(args0));
            }
        }
        return args;
    }
}
