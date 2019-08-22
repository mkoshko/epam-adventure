package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.ParagraphComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Stream;

/**
 * Parser that parse text into paragraphs.
 */
public class TextParser implements Parser {
    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger(getClass());
    /**
     * Regex for parsing string into paragraphs.
     */
    private final String regex = "(?<=\\n)(\\s{4,}|\\t)(?=\\w)";
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
     * Parse given string in to paragraphs.
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
        Stream.of(text.split(regex)).map(String::trim).forEach(elem -> {
            var paragraph = new ParagraphComposite();
            component.add(paragraph);
            if (next != null) {
                next.parse(elem, paragraph);
            }
        });
    }
}
