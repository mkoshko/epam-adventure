package by.koshko.task01.writer;

import by.koshko.task01.entity.CommonPlan;
import by.koshko.task01.utils.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class PlanToFileWriter {
    /**
     * Writes string from {@code List} to a specified file.
     * @param data to be write.
     * @param pathToFile path to file where data will be written.
     */
    public void write(final List<CommonPlan> data, final String pathToFile) {
        Path filePath = Paths.get(pathToFile);
        StringBuilder sb = new StringBuilder();
        data.stream().forEach((plan) -> sb.append(plan.toString() + "\n"));
        try {
            Logger.getLogger().info("Writing data to " + "\"" + filePath + "\"");
            Files.write(filePath, sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
