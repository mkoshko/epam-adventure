package by.koshko.task03.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Class contains one static method which reads all bytes from a specified path,
 * and returns string.
 */
public final class TextReader {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger(TextReader.class);

    /**
     * Private constructor. There is no need to create instance of this object,
     * it has one static method.
     */
    private TextReader() {
    }

    /**
     * Reads all bytes from specified file, and returns string result.
     *
     * @param path Path to a file from where bytes will be read.
     * @return string representation of file's content.
     * @throws DaoException Will thrown if argument is null or string is empty,
     *                      also if file doesn't exist or some I/O errors occur
     *                      while reading.
     */
    public static String read(final String path) throws DaoException {
        if (path == null || path.isEmpty()) {
            throw new DaoException("Path to file is empty");
        }
        try (FileInputStream fis = new FileInputStream(path)) {
            logger.info("Reading from {}", path);
            return new String(fis.readAllBytes());
        } catch (FileNotFoundException e) {
            throw new DaoException(String.format("%s at %s", e.getMessage(),
                                                             path));
        } catch (IOException e) {
            throw new DaoException(e.getMessage());
        }
    }
}
