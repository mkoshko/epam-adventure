package by.koshko.task03.service;

import by.koshko.task03.dao.DaoException;
import by.koshko.task03.dao.TextReader;
import by.koshko.task03.entity.TextComposite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static by.koshko.task03.entity.ComponentType.LEXEME;
import static by.koshko.task03.entity.ComponentType.PARAGRAPH;
import static by.koshko.task03.entity.ComponentType.SENTENCE;
import static by.koshko.task03.entity.ComponentType.SYMBOL;
import static org.testng.Assert.assertEquals;

/**
 * Positive tests for all chain of parsers.
 *
 * @see TextParser
 * @see ParagraphParser
 * @see SentenceParser
 * @see LexemeParser
 * @see SymbolParser
 */
public class TextParserTest {

    /**
     * Text parser.
     */
    private final TextParser parser = ParserHolder.getTextParser();
    /**
     * Path to a text file.
     */
    private final String path = "src/test/resources/data/text.txt";
    /**
     * String with text.
     */
    private String text = null;
    /**
     * Text composite that is a root of a tree.
     */
    private final TextComposite textComposite = new TextComposite();

    /**
     * Number of paragraphs in the text.
     */
    private final int paragraphs = 4;
    /**
     * Number of sentences in the text.
     */
    private final int sentences = 19;
    /**
     * Number of lexemes in the text.
     */
    private final int lexemes = 296;
    /**
     * Number of symbols in the text.
     */
    private final int symbols = 1536;

    @BeforeTest
    public void setUp() throws DaoException {
        text = TextReader.read(path);
        parser.parse(text, textComposite);
    }

    @Test
    public void testParse0() {
        var list = MonkeyService.obtain(textComposite, PARAGRAPH);
        assertEquals(list.size(), paragraphs);
    }

    @Test
    public void testParse1() {
        var list = MonkeyService.obtain(textComposite, SENTENCE);
        assertEquals(list.size(), sentences);
    }

    @Test
    public void testParse2() {
        var list = MonkeyService.obtain(textComposite, LEXEME);
        assertEquals(list.size(), lexemes);
    }

    @Test
    public void testParse3() {
        var list = MonkeyService.obtain(textComposite, SYMBOL);
        assertEquals(list.size(), symbols);
    }
}
