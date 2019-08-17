package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.LexemeComposite;

import java.util.stream.Stream;

public class SentenceParser implements Parser {
    private final String regex = "(?<=(\\n)|(\u0020))(?=(.))";
    private Parser next;

    @Override
    public void setNext(final Parser nextParser) {
        next = nextParser;
    }

    @Override
    public void parse(final String text, final Component component) {
        Stream.of(text.split(regex)).map(s -> {
            if (s.contains("\n")) {
                return s.trim() + "\n";
            }
            return s.trim();
        }).forEach(elem -> {
            var lexeme = new LexemeComposite();
            component.add(lexeme);
            if (next != null) {
                next.parse(elem, lexeme);
            }
        });
    }
}
