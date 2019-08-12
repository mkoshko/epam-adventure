package by.koshko.task02.service;

import by.koshko.task02.exception.ServiceException;

import java.util.List;

/**
 * Class contains method for parsing number from file which will be used for
 * {@code Matrix} object creating.
 */
public final class MatrixDataParser {
    /**
     * Private default constructor.
     */
    private MatrixDataParser() {
    }

    /**
     * Parse string to int arrays for matrix creation.
     * @param params list of strings to be parsed.
     * @return array of numbers.
     * @throws ServiceException if nothing to parse or some string cannot be
     * parsed to integers.
     */
    public static int[][] parse(final List<String> params)
            throws ServiceException {
        if (params == null) {
            throw new ServiceException("No parameters to parse");
        }
        int m = params.size();
        if (m < 1) {
            throw new ServiceException("Empty list with parameters");
        }
        int n = params.get(0).split("\\s+").length;
        if (n == 0) {
            throw new ServiceException("String is empty");
        }
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            String[] s = params.get(i).split("\\s+");
            if (s.length < n) {
                throw new ServiceException(String.format("Parsing failed."
                        + " Invalid value at row %d", i + 1));
            }
            int[] ints = new int[n];
            for (int j = 0; j < ints.length; j++) {
                try {
                    ints[j] = Integer.parseInt(s[j]);
                } catch (NumberFormatException e) {
                    throw new ServiceException(String.format("Parsing failed."
                            + " Invalid value at row %d col %d", i + 1, j));
                }
            }
            result[i] = ints;
        }
        return result;
    }
}
