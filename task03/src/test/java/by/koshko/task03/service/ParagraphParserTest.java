package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.ParagraphComposite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Test for {@link ParagraphParser#parse(String, Component)} method.
 */
public class ParagraphParserTest {

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
    @BeforeTest
    private void init() {
        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(symbolParser);
    }

    /**
     * Provides paragraphs for tests.
     * @return array with expected amount of sentences and string that is
     *         paragraph.
     */
    @DataProvider(name = "takeThisParagraph")
    private Object[][] paragraphTrader() {
        return new Object[][]{
                {3, "Sentence one. Sentence two! Sentence three?"},
                {2, "Look at Fig. 1-2, it's awesome! No way, really!"},
                {2, "Expected a mistaken line break.\nI'm in the same "
                 + "paragraph!"},
                {4, "What? What?! What's mean \"what\"?! Shut up guys..."},
                {9, "-Mom.\n-Mom.\n-Mom.\n-Mom.\n-Mom.\n-Mom.\n-Mom.\n-WHAAT?!"
                 + "\n-Nothing."}
        };
    }

    /**
     * Parse paragraph and check size of {@link ParagraphComposite}.
     *
     * @param expectedSize Expected amount of sentences.
     * @param paragraph    String to parse.
     */
    @Test(groups = {"parseTest", "paragraphParser"},
            dependsOnGroups = "sentenceParser",
            dataProvider = "takeThisParagraph")
    public void testParse(final int expectedSize, final String paragraph) {
        Component paragraphComposite = new ParagraphComposite();
        paragraphParser.parse(paragraph, paragraphComposite);
        assertEquals(paragraphComposite.size(), expectedSize);
    }
}
