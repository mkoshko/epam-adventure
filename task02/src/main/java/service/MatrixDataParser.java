package service;

import exception.ServiceException;

import java.util.List;

public final class MatrixDataParser {
    private MatrixDataParser() {
    }
    public static int[][] parse(final List<String> params) throws ServiceException {
        if (params == null) {
            throw new ServiceException("No parameters to parse");
        }
        int m = params.size();
        int n = params.get(0).split("\\s").length;
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            String[] s = params.get(i).split("\\s");
            int[] ints = new int[n];
            for (int j = 0; j < s.length; j++) {
                try {
                    ints[j] = Integer.parseInt(s[j]);
                } catch (NumberFormatException e) {
                    throw new ServiceException("Parsing failed. Invalid value"
                            + " at row " + i + " col " + j);
                }
            }
            result[i] = ints;
        }
        return result;
    }
}
