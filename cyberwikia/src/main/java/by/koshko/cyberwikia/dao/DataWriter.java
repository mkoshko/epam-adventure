package by.koshko.cyberwikia.dao;

import by.koshko.cyberwikia.service.RandomStringGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataWriter {
    private static final int FILENAME_LENGTH = 15;

    public static String write(final byte[] bytes,
                               final String contextPath,
                               final String folder,
                               final String container)
            throws DaoException {
        try {
            File file;
            String filename;
            String path;
            do {
                filename = RandomStringGenerator.generate(FILENAME_LENGTH);
                path = String.format("%s/%s/%s.%s", contextPath, folder, filename, container);
                path = path.replace("//", "/");
                file = new File(path);
            } while (file.exists());
            file.createNewFile();
            Files.write(Paths.get(path), bytes);
            return folder + filename + container;
        } catch (IOException e) {
            e.printStackTrace();
            throw new DaoException("Cannot write data.");
        }
    }
}
