package dao;

import exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {
    /**
     * Logger.
     */
    private static Logger log = LogManager.getLogger(FileReader.class);

    /**
     * Reads all data from specified file.
     *
     * @param path to file.
     * @return {@code List} with read data.
     * @throws DaoException if some I/O exceptions occurs while reading.
     */
    public List<String> read(final String path) throws DaoException {
        try {
            log.info("Reading from \"" + path + "\"");
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new DaoException("Can't read from file + " + path);
        }
    }
}
