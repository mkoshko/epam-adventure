package by.koshko.task01.dao.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

public class FileToListReader {

    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger(this);
    /**
     * {@code BufferedReader} using for read strings from a file.
     */
    private final BufferedReader reader;
    /**
     * Indicates if {@link #reader} is already empty and can be closed.
     */
    private boolean empty = false;

    /**
     * Creates a new {@code FileToListReader}, and instantiates {@link #reader}.
     *
     * @param path Path to a file.
     * @throws FileNotFoundException if the named file does not exist,
     *                               is a directory rather than a regular file,
     *                               or for some other reason cannot be opened
     *                               for reading.
     */
    public FileToListReader(final String path) throws FileNotFoundException {
        if (path != null) {
            reader = new BufferedReader(new FileReader(path));
            logger.debug("Reader is created, filepath = " + "\'" + path + "\'");
        } else {
            throw new FileNotFoundException("Path to file is empty");
        }
    }

    /**
     * Reads all existent strings from {@link #reader},
     * and put each line to {@code List}.
     *
     * @return {@code List} contains all read strings.
     * @throws IOException If an I/O error occurs.
     */
    public List<String> readAllLines() throws IOException {
        List<String> lines = new ArrayList<>();
        String s;
        try {
            while ((s = reader.readLine()) != null) {
                if (s.length() != 0 && !s.startsWith("#")) {
                    lines.add(s);
                }
            }
        } finally {
            close(reader);
        }
        return lines;
    }

    /**
     * Reads as much lines as specified in quantity.
     *
     * @param quantity The number of lines to read.
     * @return {@code List} contains all read strings.
     * @throws IOException If an I/O error occurs.
     */
    public List<String> nextLines(final int quantity) throws IOException {
        List<String> lines = new ArrayList<>();
        String s;
        for (int i = 0; i < quantity;) {
            try {
                s = reader.readLine();
                if (s != null) {
                    if (s.length() != 0 && !s.startsWith("#")) {
                        lines.add(s);
                        i++;
                    }
                } else {
                    empty = true;
                    break;
                }
            } finally {
                if (empty) {
                    close(reader);
                }
            }
        }
        return lines;
    }

    /**
     * Reads next available line from {@link #reader}. Skips empty lines, until
     * find non empty line. If all lines was empty, returns empty string.
     *
     * @return The read string, or empty string if all lines was empty;
     * @throws IOException If an I/O error occurs.
     */
    public String nextLine() throws IOException {
        String s;
        try {
            while ((s = reader.readLine()) != null) {
                if (s.length() != 0 && !s.startsWith("#")) {
                    return s;
                }
            }
            empty = true;
        } finally {
            if (empty) {
                close(reader);
            }
        }
        return "";
    }

    private void close(final Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            logger.error("Attempt to close reader is failed", e);
        }
    }

}
