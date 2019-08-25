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
public class SortingServiceTest4 {

    /**
     * Original text.
     */
    private String textToSort = "Wake up Alan! Wake up! Word with no A.";
    /**
     * Sorted text.
     */
    private String expectedText = "\tAlan! Wake up Wake up! A. Word no with";

    /**
     * Sort words in sentences by number of specific char and compare
     * with expected.
     *
     * @throws ServiceException see {@link SortingService} docs.
     */
    @Test
    public void testSortByCharsNumber() throws ServiceException {
        TextComposite text = new TextComposite();
        ParserHolder.getTextParser().parse(textToSort, text);
        SortingService.sortByCharsNumber(text, 'a');
        assertEquals(text.compose(), expectedText);
    }
}
