package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.SentenceComposite;

import java.util.stream.Stream;

public class ParagraphParser implements Parser {
    private final String regex = "(?<=[.])\\s+(?=[A-Z]|[(])";
    private Parser next;

    @Override
    public void setNext(final Parser nextParser) {
        next = nextParser;
    }

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
