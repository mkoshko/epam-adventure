package by.koshko.task03.dao;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Tests for {@code TextReader} class.
 */
public class TextReaderTest {
    /**
     * Path to text file.
     */
    private final String path = getClass()
            .getResource("/data/testText.txt").getPath();
    /**
     * Expected string with text.
     */
    private final String expectedText = "An operating system (OS) is system"
            + " software that manages computer hardware\nand software resources"
            + " and provides common services for computer programs.";

    /**
     * Positive test for reading from file.
     *
     * @throws DaoException if path argument is null or empty.
     */
    @Test(groups = "textReader")
    public void testRead() throws DaoException {
        String actual = TextReader.read(path);
        assertEquals(expectedText, actual);
    }

    /**
     * Negative test, when argument for {@link TextReader#read(String)} is null.
     *
     * @throws DaoException if path argument is null or empty.
     */
    @Test(expectedExceptions = DaoException.class)
    public void testReadNull() throws DaoException {
        String actual = TextReader.read(null);
    }

    /**
     * Negative test, when argument for {@link TextReader#read(String)} is null.
     *
     * @throws DaoException if path argument is null or empty.
     */
    @Test(expectedExceptions = DaoException.class)
    public void testReadEmpty() throws DaoException {
        String actual = TextReader.read("");
    }
}
