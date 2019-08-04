package dao;

import entity.Matrix;
import exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MatrixWriter {
    /**
     * Logger.
     */
    private static Logger log = LogManager.getLogger(MatrixWriter.class);
    /**
     * Output stream for data writing.
     */
    private FileOutputStream fos = null;

    public MatrixWriter(final String path) throws DaoException {
        try {
            log.info("Creating output stream to " + path);
            fos = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            throw new DaoException("Can't write to a specified file " + path);
        }
    }

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
