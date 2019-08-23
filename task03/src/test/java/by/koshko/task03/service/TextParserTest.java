package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.TextComposite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Test for {@link TextParser#parse(String, Component)} method.
 */
public class TextParserTest {
    /**
     * Text parser.
     */
    private TextParser textParser = new TextParser();
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
        textParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(symbolParser);
    }
    /**
     * Provides text for tests.
     *
     * @return array with text and expected amount of paragraphs.
     */
    @DataProvider(name = "writer")
    private Object[][] textProvider() {
        return new Object[][] {
                {2, "    Four spaces in front of me. And one in front of me.\n"
                 + "    Again four spaces?!     This four spaces shouldn't"
                 + " count, and it still the same paragraph."},
                {2, "\tWow tab! Wow tab?! Yep this is a tab!\n\tWe have tab too"
                 + "\tTab without new line? Should it be a new paragraph? NO!"},
                {1, "\tParagraph one.\n   Three spaces? No no no. Still the "
                 + "same paragraph."}
        };
    }

    /**
     * Parse text to paragraphs and checks size of {@link TextComposite}.
     * @param expectedParagraphs Amount of expected paragraphs.
     * @param text Text to parse.
     */
    @Test(groups = {"parseTest", "textParser"},
            dependsOnGroups = "paragraphParser",
            dataProvider = "writer")
    public void parseText(final int expectedParagraphs, final String text) {
        Component textComponent = new TextComposite();
        textParser.parse(text, textComponent);
        assertEquals(textComponent.size(), expectedParagraphs);

    }
}
