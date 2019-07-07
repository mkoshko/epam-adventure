package by.koshko.task01.reader;

import by.koshko.task01.utils.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileToListReader {

    /**
     * Default empty constructor.
     */
    public FileToListReader() {
    }

    /**
     * Read data from file to {@code List} separated
     * by newline.
     *
     * @param path the path of the referenced file.
     * @return {@code List} with read data.
     */
    public List<String> read(final String path) {
        Path filePath = Paths.get(path);
        List<String> data = new ArrayList<>();
        try (Stream<String> stream = Files.lines(filePath)) {
            Logger.getLogger().info("Reading data from " + "\"" + filePath + "\"");
            data = stream.collect(Collectors.toList());
        } catch (IOException e) {
            Logger.getLogger().error("\"" + path + "\"" + " doesn't exist or it's a directory");
            e.printStackTrace();
        }
        return data;
    }

}
