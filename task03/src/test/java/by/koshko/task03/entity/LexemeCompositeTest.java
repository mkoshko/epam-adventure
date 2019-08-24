package by.koshko.task03.entity;

import by.koshko.task03.service.LexemeParser;
import by.koshko.task03.service.SymbolParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Test for {@link LexemeComposite#compose()} method.
 */
public class LexemeCompositeTest {

    /**
     * Lexeme parser.
     */
    private LexemeParser lexemeParser = new LexemeParser();
    /**
     * Symbol parser.
     */
    private SymbolParser symbolParser = new SymbolParser();

    /**
     * Provides words without punctuation marks.
     *
     * @return arrays with strings.
     */
    @DataProvider(name = "lexemeProvider")
    private Object[][] provide() {
        return new Object[][]{
                {"Word"},
                {"W(o)rd"},
                {"W-o-r-D"},
                {"Wo_rd"},
                {"_01Word01_"},
                {"$word"},
                {"123456"},
        };
    }

    /**
     * Provides words with punctuation marks.
     *
     * @return arrays with strings.
     */
    @DataProvider(name = "lexemeWithMark")
    private Object[][] provide0() {
        return new Object[][]{
                {"Word!"},
                {"W(o)rd!!!"},
                {"W-o-r-D?!"},
                {"Wo_rd?"},
                {"_01Word01_???"},
                {"$word."},
                {"123456..."},
                {""}
        };
    }

    /**
     * Test {@link LexemeComposite#compose()} method, check if composed word
     * equals to original word.
     *
     * @param word Original word.
     */
    @Test(groups = {"componentTest", "lexemeComponent"},
            dependsOnGroups = "parseTest",
            dataProvider = "lexemeProvider")
    public void testCompose(final String word) {
        lexemeParser.setNext(symbolParser);
        Component lexeme = new LexemeComposite();
        lexemeParser.parse(word, lexeme);
        assertEquals(lexeme.getChild(0).compose(), word);
    }

    /**
     * Test {@link LexemeComposite#compose()} method, check if composed word
     * equals to original word and contains punctuation mark.
     *
     * @param word Original word.
     */
    @Test(groups = {"componentTest", "lexemeComponent"},
            dependsOnGroups = "parseTest",
            dataProvider = "lexemeWithMark")
    public void testCompose2(final String word) {
        lexemeParser.setNext(symbolParser);
        Component lexeme = new LexemeComposite();
        lexemeParser.parse(word, lexeme);
        assertEquals(lexeme.compose(), word);
    }
}
