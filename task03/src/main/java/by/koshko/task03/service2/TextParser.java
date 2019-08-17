package by.koshko.task03.service2;

import by.koshko.task03.bean.Component;
import by.koshko.task03.bean.ParagraphComposite;

import java.util.stream.Stream;

public class TextParser implements Parser {
    private final String regex = "(?<=\\n)(\\s{4,}|\\t)(?=\\w)";
    private Parser next;

    @Override
    public void setNext(final Parser nextParser) {
        next = nextParser;
    }

    @Override
    public void parse(final String text, final Component component) {
        Stream.of(text.split(regex)).map(String::trim).forEach(elem -> {
            var paragraph = new ParagraphComposite();
            component.add(paragraph);
            if (next != null) {
                next.parse(elem, paragraph);
            }
        });
    }
}
