package by.koshko.task01.dao.reader;

import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.testng.Assert.*;

public class FileToListReaderFailTest {

    @Test(expectedExceptions = FileNotFoundException.class)
    public void readerTestInvalidPath() throws FileNotFoundException {
        FileToListReader reader = new FileToListReader("fileNOtFound.txt");
    }

    @Test(expectedExceptions = FileNotFoundException.class)
    public void readerTestNullPath() throws FileNotFoundException {
        FileToListReader reader = new FileToListReader(null);
    }

}