package by.koshko.task03.entity;

import by.koshko.task03.dao.DaoException;
import by.koshko.task03.dao.TextReader;
import by.koshko.task03.service.ParserHolder;
import by.koshko.task03.service.TextParser;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Tests for {@link TextComposite#compose()}.
 */
public class TextCompositeTest {

    /**
     * Text parser.
     */
    private TextParser textParser = ParserHolder.getTextParser();
    /**
     * Text.
     */
    private String text;

    /**
     * Reads text from file.
     *
     * @throws DaoException .
     */
    private void readText() throws DaoException {
        text = TextReader.read(getClass()
                .getResource("/data/text.txt").getPath());
    }

    /**
     * Parse and the compose text and checks if composed text equals to
     * original.
     *
     * @throws DaoException .
     */
    @Test(groups = {"componentTest", "textComponent"},
            dependsOnGroups = {"parseTest", "textReader"})
    public void testCompose() throws DaoException {
        readText();
        Component textComposite = new TextComposite();
        textParser.parse(text, textComposite);
        assertEquals(textComposite.compose(), text);
    }
}
