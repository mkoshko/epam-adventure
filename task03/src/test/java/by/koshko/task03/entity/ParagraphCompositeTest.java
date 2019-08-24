package by.koshko.task03.entity;

import by.koshko.task03.service.LexemeParser;
import by.koshko.task03.service.ParagraphParser;
import by.koshko.task03.service.SentenceParser;
import by.koshko.task03.service.SymbolParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Test for {@link ParagraphComposite#compose()} method.
 */
public class ParagraphCompositeTest {
    /**
     * Paragraph parser.
     */
    private ParagraphParser paragraphParser = new ParagraphParser();
    /**
     * Sentence parser.
     */
    private SentenceParser sentenceParser = new SentenceParser();
    /**
     * Lexeme parser.
     */
    private LexemeParser lexemeParser = new LexemeParser();
    /**
     * Symbol parser.
     */
    private SymbolParser symbolParser = new SymbolParser();

    /**
     * Creates chain of parsers.
     */
    private void setUp() {
        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(symbolParser);
    }

    /**
     * Provides paragraphs for tests.
     *
     * @return arrays with strings.
     */
    @DataProvider(name = "paragraphs")
    private Object[][] provide() {
        return new Object[][]{
                {"Google Go has been released from its cage and unleashed"
                        + " on Android devices around the world! It's basically"
                        + " weightless but still maintains useful functionality"
                        + " allowing you to search for things in a smarter way?"
                        + " Let's take a look at what it actually is and if you"
                        + " need to bother downloading it..."},
                {"Google Go is a lightweight app, at just seven megabytes,"
                        + " allowing you to do a bunch of fun things the more"
                        + " expensive, heavyweight phones can do. It allows"
                        + " users to use Google Lens features like searching"
                        + " and translating with your camera, calculating"
                        + " restaurant bills by pointing at the order as well"
                        + " as dictating text on your browser and using"
                        + " voice-activated search."}
        };
    }

    /**
     * Test compose method. Checks if composed string equals to original string.
     *
     * @param paragraph String to parse and then compose.
     */
    @Test(groups = {"componentTest", "paragraphComponent"},
            dependsOnGroups = "parseTest",
            dataProvider = "paragraphs")
    public void testCompose(final String paragraph) {
        setUp();
        Component paragraphComposite = new ParagraphComposite();
        paragraphParser.parse(paragraph, paragraphComposite);
        assertEquals(paragraphComposite.compose(), paragraph);
    }
}
