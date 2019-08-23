package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.ComponentType;
import by.koshko.task03.entity.LexemeComposite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Tests for {@link LexemeParser#parse(String, Component)} method.
 */
public class LexemeParserTest {

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
    private void initParser() {
        lexemeParser.setNext(symbolParser);
    }

    /**
     * Provides lexemes with marks for test purposes.
     *
     * @return array with strings.
     */
    @DataProvider(name = "whoWantSomeLexemes?")
    private Object[][] lexemeFactory() {
        return new Object[][]{
                {"lexemeWithMark,"},
                {"lexemeWithMark!"},
                {"lexemeWithMark!!!"},
                {"lexemeWithMark?!"},
                {"lexemeWithMark?"},
                {"lexemeWithMark???"},
                {"lexemeWithMark."},
                {"lexemeWithMark..."}
        };
    }

    /**
     * Provides lexemes with marks(not punctuation mark).
     *
     * @return array with strings.
     */
    @DataProvider(name = "wannaMoreLexemes?")
    private Object[][] lexemeFactory2() {
        return new Object[][]{
                {"lexeme(With)Mark"},
                {"lexeme\"With\"Mark"},
                {"lexeme-With-Mark"},
                {"lexeme'With'Mark"},
                {"lexeme{With}Mark"},
        };
    }

    /**
     * Parse lexeme with punctuations marks and checks lexeme composite size.
     * Must be 2.
     *
     * @param lexeme Lexeme to parse.
     */
    @Test(groups = {"parseTest", "lexemeParser"},
            dependsOnGroups = {"symbolParser"},
            dataProvider = "whoWantSomeLexemes?")
    public void parseLexemesWithMark(final String lexeme) {
        var lexemeComposite = new LexemeComposite();
        lexemeParser.parse(lexeme, lexemeComposite);
        assertEquals(lexemeComposite.size(), 2);
    }

    /**
     * Parse lexeme with punctuations marks and checks lexeme first child type.
     * Must be word.
     *
     * @param lexeme Lexeme to parse.
     */
    @Test(groups = {"parseTest", "lexemeParser"},
            dependsOnGroups = "symbolParser",
            dataProvider = "whoWantSomeLexemes?")
    public void parseLexemesWithMark2(final String lexeme) {
        Component lexemeComposite = new LexemeComposite();
        lexemeParser.parse(lexeme, lexemeComposite);
        assertEquals(lexemeComposite.getChild(0).getType(),
                ComponentType.WORD);
    }

    /**
     * Parse lexeme with punctuation marks and checks lexeme second child type.
     * Must be mark.
     *
     * @param lexeme Lexeme to parse.
     */
    @Test(groups = {"parseTest", "lexemeParser"},
            dependsOnGroups = "symbolParser",
            dataProvider = "whoWantSomeLexemes?")
    public void parseLexemesWithMark3(final String lexeme) {
        var lexemeComposite = new LexemeComposite();
        lexemeParser.parse(lexeme, lexemeComposite);
        assertEquals(lexemeComposite.getChild(1).getType(),
                ComponentType.MARK);
    }

    /**
     * Parse lexeme with marks, checks lexeme first child type. Must be word.
     *
     * @param lexeme Lexeme to parse.
     */
    @Test(groups = {"parseTest", "lexemeParser"},
            dependsOnGroups = "symbolParser",
            dataProvider = "wannaMoreLexemes?")
    public void parseLexemesWithMark4(final String lexeme) {
        var lexemeComposite = new LexemeComposite();
        lexemeParser.parse(lexeme, lexemeComposite);
        assertEquals(lexemeComposite.getChild(0).getType(),
                ComponentType.WORD);
    }

    /**
     * Parse lexeme with marks, checks lexeme size. Must be 1.
     *
     * @param lexeme Lexeme to parse.
     */
    @Test(groups = {"parseTest", "lexemeParser"},
            dependsOnGroups = "symbolParser",
            dataProvider = "wannaMoreLexemes?")
    public void parseLexemesWithMark5(final String lexeme) {
        var lexemeComposite = new LexemeComposite();
        lexemeParser.parse(lexeme, lexemeComposite);
        assertEquals(lexemeComposite.size(), 1);
    }
}
