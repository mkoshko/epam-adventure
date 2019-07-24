package by.koshko.task01.dao.writer;

import by.koshko.task01.entity.Plan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Closeable;
import java.util.List;

public final class PlanDescriptionToFileWriter {
    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger(this);
    /**
     * Output stream of this {@code PlanDescriptionToFileWriter}.
     */
    private FileOutputStream writer;

    /**
     * Creates {@code PlanDescriptionToFileWriter}.
     *
     * @param path Path to a file where data will be written.
     * @throws FileNotFoundException if the file exists but is a directory
     *         rather than a regular file, does not exist but cannot be created,
     *         or cannot be opened for any other reason.
     */
    public PlanDescriptionToFileWriter(final String path)
            throws FileNotFoundException {
        if (path != null) {
            writer = new FileOutputStream(path);
        } else {
            logger.info("Null path reference");
            throw new FileNotFoundException("Null path reference");
        }
    }

    /**
     * Writes plans description to a specified file.
     *
     * @param plans to be write.
     * @throws IOException If an I/O error occurs.
     */
    public void write(final List<Plan> plans) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (plans != null) {
            plans.forEach((plan) -> {
                if (plan != null) {
                    sb.append(plan.toString()).append("\n");
                }
            });
            try {
                writer.write(sb.toString().getBytes());
                writer.flush();
            } finally {
                close(writer);
            }
        } else {
            logger.info("Nothing to write to a file.");
        }
    }

    private void close(final Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            logger.error("Attempt to close writer is failed", e);
        }
    }

}
