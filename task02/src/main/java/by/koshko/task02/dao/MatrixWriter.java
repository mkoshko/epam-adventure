package by.koshko.task02.dao;

import by.koshko.task02.entity.Matrix;
import by.koshko.task02.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Class used for writing matrices to a file.
 */
public class MatrixWriter {
    /**
     * Logger.
     */
    private static Logger log = LogManager.getLogger(MatrixWriter.class);
    /**
     * Output stream for data writing.
     */
    private FileOutputStream fos = null;

    /**
     * Creates {code MatrixWriter} and initialize {@code FileOutputStream}.
     *
     * @param path to file.
     * @throws DaoException if file not found or it's a directory.
     */
    public MatrixWriter(final String path) throws DaoException {
        try {
            log.info(String.format("Creating output stream to %s", path));
            fos = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            throw new DaoException(String.format("Can't write to a specified"
                    + " file %s", path));
        }
    }

    /**
     * Writes {@code Matrix} to file.
     *
     * @param matrix to be written to a file.
     * @throws DaoException if some I/O exceptions occurs.
     */
    public void write(final Matrix matrix) throws DaoException {
        try {
            log.info("Writing matrix to a file");
            fos.write(matrix.toString().getBytes());
            close(fos);
            log.info("Writing is complete");
        } catch (IOException e) {
            throw new DaoException("Can't write matrix to a file");
        } finally {
            close(fos);
        }
    }

    /**
     * Closes output stream.
     *
     * @param closeable object to be closed.
     */
    private void close(final Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            log.error("Attempt to close writer is failed", e);
        }
    }
}
