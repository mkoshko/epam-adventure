package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.LexemeComposite;

import java.util.stream.Stream;

/**
 * Parse sentence into lexemes.
 */
public class SentenceParser implements Parser {
    /**
     * Regex for split sentence into lexemes.
     */
    private final String regex = "\\s+";
    /**
     * Next parser in chain.
     */
    private Parser next;

    /**
     * Sets the next parser in chain.
     *
     * @param nextParser next parser in chain.
     */
    @Override
    public void setNext(final Parser nextParser) {
        next = nextParser;
    }

    /**
     * Parse string which must represent a sentence into a lexemes.
     *
     * @param text      string to be parsed.
     * @param component in which parsed parts will be stored.
     */
    @Override
    public void parse(final String text, final Component component) {
        Stream.of(text.split(regex)).map(String::trim).forEach(elem -> {
            var lexeme = new LexemeComposite();
            component.add(lexeme);
            if (next != null) {
                next.parse(elem, lexeme);
            }
        });
    }
}
