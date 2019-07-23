package by.koshko.task01.dao.reader;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class FileToListReaderTest {

    private List<String> expected = new ArrayList<>();
    private final String s0 = "If you get this message, you are on the right way.";
    private final String s1 = "Moreover, if you either get this one, and your list length " +
                              "equals 2, you're qualified to work in Google.";
    private final String s2 = "Is length 3, right? Good!";
    private final String filePath = "data/reader_test.txt";

    @BeforeTest
    private void init() {
        expected.add(s0);
        expected.add(s1);
        expected.add(s2);
    }

    @Test
    public void testReadAllLines() throws IOException {
        FileToListReader reader = new FileToListReader(filePath);
        List<String> result = reader.readAllLines();
        assertEquals(expected, result);
        assertEquals(result.size(), 3);
    }

    @Test
    public void testNextLines() throws IOException {
        FileToListReader reader = new FileToListReader(filePath);
        List<String> result = reader.nextLines(2);
        assertEquals(expected.subList(0,2), result);
        assertEquals(result.size(), 2);
    }

    @Test
    public void testNextLine() throws IOException {
        FileToListReader reader = new FileToListReader(filePath);
        String result = reader.nextLine();
        assertEquals(expected.get(0), result);
    }
}