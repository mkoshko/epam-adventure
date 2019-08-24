package by.koshko.task03.entity;

import by.koshko.task03.service.LexemeParser;
import by.koshko.task03.service.SentenceParser;
import by.koshko.task03.service.SymbolParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Test for {@link SentenceComposite#compose()} method.
 */
public class SentenceCompositeTest {

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
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(symbolParser);
    }

    /**
     * Provides sentences for tests.
     *
     * @return array with strings.
     */
    @DataProvider(name = "sentenceProvider")
    private Object[][] provide() {
        return new Object[][]{
                {"Expected sentence!", "Expected sentence!"},
                {"Sentence without spaces.", "Sentence     without spaces."},
                {"Sentence without new line.", "Sentence without\nnew line."},
                {"Sentence without tab?!", "Sentence\twithout tab?!"}
        };
    }

    /**
     * Test for {@link SentenceComposite#compose()} method.
     *
     * @param expected expected string after compose.
     * @param sentence string to parse and then compose.
     */
    @Test(groups = {"componentTest", "sentenceTest"},
            dependsOnGroups = "parseTest",
            dataProvider = "sentenceProvider")
    public void testCompose(final String expected, final String sentence) {
        setUp();
        Component sentenceComposite = new SentenceComposite();
        sentenceParser.parse(sentence, sentenceComposite);
        assertEquals(sentenceComposite.compose(), expected);
    }
}
