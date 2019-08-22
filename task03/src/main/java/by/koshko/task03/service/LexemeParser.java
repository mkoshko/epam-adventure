package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.MarkComposite;
import by.koshko.task03.entity.WordComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Stream;

/**
 * Parser that parse lexemes into word and punctuation marks.
 */
public class LexemeParser implements Parser {
    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger(getClass());
    /**
     * Regex for parsing lexemes onto words and punctuation marks.
     */
    private String regex = "(?<=[^.,?!])(?=[.,?!])";
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
     * Split string that must represent a lexeme into a word and punctuation
     * mark.
     *
     * @param text      string to be parsed.
     * @param component in which parsed parts will be stored.
     */
    @Override
    public void parse(final String text, final Component component) {
        if (text == null || component == null) {
            logger.warn("Null arguments passed to parse method");
            return;
        }
        Stream.of(text.split(regex)).forEach(s -> {
            if (s.matches("[^,.?!]+")) {
                var word = new WordComposite();
                component.add(word);
                if (next != null) {
                    next.parse(s, word);
                }
            } else if (s.matches("[,.?!]+")) {
                var mark = new MarkComposite();
                component.add(mark);
                if (next != null) {
                    next.parse(s, mark);
                }
            }
        });
    }
}
