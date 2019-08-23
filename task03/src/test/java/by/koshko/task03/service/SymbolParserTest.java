package by.koshko.task03.service;

import by.koshko.task03.entity.WordComposite;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Tests for {@code SymbolParser}.
 */
public class SymbolParserTest {

    /**
     * SymbolParser himself.
     */
    private SymbolParser symbolParser = new SymbolParser();

    /**
     * Parse word and check composite length.
     */
    @Test(groups = {"parseTest", "symbolParser"})
    public void testParse() {
        var word = new WordComposite();
        var string = "Test";
        symbolParser.parse(string, word);
        assertEquals(word.size(), string.toCharArray().length);
    }
    /**
     * Try to parse when string argument is null.
     */
    @Test(groups = {"parseTest", "symbolParser"})
    public void testParseNull() {
        var word = new WordComposite();
        symbolParser.parse(null, word);
        assertEquals(word.size(), 0);
    }
    /**
     * Try to parse when composite argument is null.
     */
    @Test(groups = {"parseTest", "symbolParser"})
    public void testParseNull2() {
        symbolParser.parse("noMatterWhat", null);
    }
}
