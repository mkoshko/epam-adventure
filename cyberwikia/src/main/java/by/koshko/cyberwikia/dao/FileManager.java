package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.bean.RawData;
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
                               final String container) {
        try {
            File file;
            String filename;
            String path;
            do {
                filename = RandomStringGenerator.generate(FILENAME_LENGTH);
                path = String.format("%s%s/%s.%s", RawData.getRootPath(), folder, filename, container);
                file = new File(path);
            } while (file.exists());
            if (!file.createNewFile()) {
                logger.debug("Cannot create new file.");
                return null;
            }
            Files.write(Paths.get(path), bytes);
            return String.format("%s/%s.%s", folder, filename, container);
        } catch (IOException e) {
            logger.error("Cannot write data. {}", e.getMessage());
            return null;
        }
    }

    public static void delete(final String relativePath) throws DaoException {
        try {
            Path path = Paths.get(String.format("%s%s", RawData.getRootPath(), relativePath));
            if (Files.deleteIfExists(path)) {
                logger.debug("File '{}' is deleted.", relativePath);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new DaoException("Cannot delete file");
        }
    }
}
