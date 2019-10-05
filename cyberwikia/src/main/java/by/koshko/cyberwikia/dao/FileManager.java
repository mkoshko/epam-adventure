package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.bean.ApplicationPath;
import by.koshko.cyberwikia.service.RandomStringGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    private static Logger logger = LogManager.getLogger(FileManager.class);
    private static final int FILENAME_LENGTH = 15;

    public static String save(final byte[] bytes,
                               final String folder,
                               final String container)
            throws DaoException {
        try {
            File file;
            String filename;
            String path;
            do {
                filename = RandomStringGenerator.generate(FILENAME_LENGTH);
                path = String.format("%s%s/%s.%s", ApplicationPath.getRoot(), folder, filename, container);
                file = new File(path);
            } while (file.exists());
            logger.debug("File path: {}", path);
            file.createNewFile();
            Files.write(Paths.get(path), bytes);
            return String.format("%s/%s.%s", folder, filename, container);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DaoException("Cannot write data.");
        }
    }

    public static void delete(final String relativePath) throws DaoException {
        if (relativePath == null || relativePath.isBlank()) {
            logger.debug("Path to file is empty.");
            return;
        }
        try {
            logger.debug("Attempt to delete file.");
            Path path = Paths.get(String.format("%s%s", ApplicationPath.getRoot(), relativePath));
            logger.debug("File path: {}", path);
            if (Files.deleteIfExists(path)) {
                logger.debug("{} deleted.", relativePath);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new DaoException("Cannot delete file");
        }
    }
}
