package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.MarkComposite;
import by.koshko.task03.entity.WordComposite;

import java.util.stream.Stream;

public class LexemeParser implements Parser {
    private String regex = "(?<=\\W)(?=\\w)|(?<=\\w)(?=\\W)";
    private Parser next;

    @Override
    public void setNext(final Parser nextParser) {
        next = nextParser;
    }

    @Override
    public void parse(final String text, final Component component) {
        Stream.of(text.split(regex)).forEach(s -> {
            if (s.matches("\\w+")) {
                var word = new WordComposite();
                component.add(word);
                if (next != null) {
                    next.parse(s, word);
                }
            } else if (s.matches("\\W+")) {
                var mark = new MarkComposite();
                component.add(mark);
                if (next != null) {
                    next.parse(s, mark);
                }
            }
        });
    }
}
