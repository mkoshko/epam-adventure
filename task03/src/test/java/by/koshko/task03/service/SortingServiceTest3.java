package by.koshko.task03.service;

import by.koshko.task03.dao.DaoException;
import by.koshko.task03.dao.TextReader;
import by.koshko.task03.entity.TextComposite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Tests for {@link SortingService} methods.
 */
public class SortingServiceTest3 {

    /**
     * Original text.
     */
    private String textToSort;
    /**
     * Sorted text.
     */
    private String expectedText;
    /**
     * Path to original text.
     */
    private final String path = getClass()
            .getResource("/data/sortWordsLength.txt").getPath();
    /**
     * Path to sorted text.
     */
    private final String pathToSorted = getClass()
            .getResource("/data/sortedWordsLength.txt").getPath();

    /**
     * Read text from file.
     *
     * @throws DaoException see {@link TextReader} docs.
     */
    @BeforeTest
    public void setUp() throws DaoException {
        textToSort = TextReader.read(path);
        expectedText = TextReader.read(pathToSorted);
    }

    /**
     * Sort words in sentences by length and compare with expected.
     *
     * @throws ServiceException see {@link SortingService} docs.
     */
    @Test
    public void testSortByWordLength() throws ServiceException {
        TextComposite text = new TextComposite();
        ParserHolder.getTextParser().parse(textToSort, text);
        SortingService.sortByWordsLength(text);
        assertEquals(text.compose(), expectedText);
    }
}
