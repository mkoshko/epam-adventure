package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.SentenceComposite;

import java.util.stream.Stream;

/**
 * Parses string into sentences.
 */
public class ParagraphParser implements Parser {
    /**
     * Regex for split paragraph into sentences.
     */
    private final String regex = "(?<=[.!?])\\s+(?=[A-Z])";
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
     * Parse given string which must represent a paragraph into sentences.
     *
     * @param text      string to be parsed.
     * @param component in which parsed parts will be stored.
     */
    @Override
    public void parse(final String text, final Component component) {
        Stream.of(text.split(regex))
                .forEach(elem -> {
                    var sentence = new SentenceComposite();
                    component.add(sentence);
                    if (next != null) {
                        next.parse(elem, sentence);
                    }
                });
    }
}
