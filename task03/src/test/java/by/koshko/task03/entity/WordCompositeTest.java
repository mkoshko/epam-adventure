package by.koshko.task03.entity;

import by.koshko.task03.service.LexemeParser;
import by.koshko.task03.service.SymbolParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Test for {@link WordComposite#compose()} method.
 */
public class WordCompositeTest {

    /**
     * Lexeme parser.
     */
    private LexemeParser lexemeParser = new LexemeParser();
    /**
     * Symbol parser.
     */
    private SymbolParser symbolParser = new SymbolParser();

    /**
     * Provide words for tests.
     *
     * @return array with strings.
     */
    @DataProvider(name = "words")
    private Object[][] provide() {
        return new Object[][]{
                {"Word"},
                {"Wo(R)d"},
                {"Wo01Rd"},
                {"wo_RD"},
                {"_wo_RD)("},
                {"80rD"},
        };
    }

    /**
     * Checks if compose() method return the same string.
     *
     * @param word to parse and compose.
     */
    @Test(groups = {"componentTest", "wordComponent"},
            dependsOnGroups = "parseTest",
            dataProvider = "words")
    public void testCompose(final String word) {
        lexemeParser.setNext(symbolParser);
        Component lexeme = new LexemeComposite();
        lexemeParser.parse(word, lexeme);
        assertEquals(lexeme.getChild(0).compose(), word);
    }

}
