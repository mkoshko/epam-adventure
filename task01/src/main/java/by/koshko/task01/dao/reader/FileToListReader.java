package by.koshko.task01.dao.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileToListReader {

    private final BufferedReader reader;

    public FileToListReader(final String path) throws FileNotFoundException {
        reader = path != null ? new BufferedReader(new FileReader(path)) : null;
    }

    public List<String> readAllLines() throws IOException {
        List<String> lines = new ArrayList<>();
        String s;
        while ((s = reader.readLine()) != null) {
            lines.add(s);
        }
        return lines;
    }

    public List<String> nextLines(final int quantity) throws IOException {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            String s = reader.readLine();
            if (s != null) {
                lines.add(s);
            } else {
                return lines;
            }
        }
        return lines;
    }

    public String nextLine() throws IOException {
        return reader.readLine();
    }

    @Override
    protected void finalize() throws Throwable {
        reader.close();
    }

}
