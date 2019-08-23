package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.SentenceComposite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Test for {@link SentenceParser#parse(String, Component)} method.
 */
public class SentenceParserTest {

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
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(symbolParser);
    }

    /**
     * Provide sentences for tests.
     * @return array with string and number of lexemes in corresponding
     *         sentence.
     */
    @DataProvider(name = "sentenceMaker")
    private Object[][] makeSentence() {
        return new Object[][]{
                {4, "Simple sentence, nothing special."},
                {1, "OneWordSentence."},
                {7, "Sentence with       a lot of space characters!"},
                {12, "Sentence with\nmistaken line break, but this"
                        + " is still the same sentence!?"},
                {0, ""}
        };
    }

    /**
     * Parse sentence to lexemes and checks {@link SentenceComposite} size.
     *
     * @param expected Expected sentence composite.
     * @param sentence String to parse.
     */
    @Test(groups = {"parseTest", "sentenceParser"},
            dependsOnGroups = "lexemeParser",
            dataProvider = "sentenceMaker")
    public void parseSentence(final int expected, final String sentence) {
        Component sentenceComposite = new SentenceComposite();
        sentenceParser.parse(sentence, sentenceComposite);
        assertEquals(sentenceComposite.size(), expected);
    }
}
