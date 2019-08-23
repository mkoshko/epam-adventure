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

    @Test(groups = {"parseTest", "lexemeParser"},
            dependsOnGroups = {"symbolParser"},
            dataProvider = "whoWantSomeLexemes?")
    public void parseLexemesWithMark(final String lexeme) {
        var lexemeComposite = new LexemeComposite();
        lexemeParser.parse(lexeme, lexemeComposite);
        assertEquals(lexemeComposite.size(), 2);
    }

    @Test(groups = {"parseTest", "lexemeParser"},
            dependsOnGroups = "symbolParser",
            dataProvider = "whoWantSomeLexemes?")
    public void parseLexemesWithMark2(final String lexeme) {
        var lexemeComposite = new LexemeComposite();
        lexemeParser.parse(lexeme, lexemeComposite);
        assertEquals(lexemeComposite.getChild(1).getType(),
                ComponentType.MARK);
    }
}