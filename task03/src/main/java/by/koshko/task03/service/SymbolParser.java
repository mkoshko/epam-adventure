package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.Symbol;

import java.util.stream.IntStream;

/**
 * The last parser in chain which split string into a symbols.
 */
public class SymbolParser implements Parser {
    /**
     * Throws {@link UnsupportedOperationException} because it is the last
     * parser in chain.
     *
     * @param nextParser next parser in chain.
     */
    @Override
    public void setNext(final Parser nextParser) {
        throw new UnsupportedOperationException();
    }

    /**
     * Split string in to chars.
     *
     * @param text      string to be parsed.
     * @param component in which parsed parts will be stored.
     */
    @Override
    public void parse(final String text, final Component component) {
        char[] chars = text.toCharArray();
        IntStream.range(0, chars.length)
                .forEach(i -> component.add(new Symbol(chars[i])));
    }
}
