package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.Composite;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractParser implements Parser {
    private Parser nextParser;
    private String regularExpression;
    private Composite.Type type;

    public void setRegex(final String regex) {
        regularExpression = regex;
    }

    public void setType(final Composite.Type compositeType) {
        type = compositeType;
    }

    public Parser getNextParser() {
        return nextParser;
    }

    public void setNext(final Parser parser) {
        nextParser = parser;
    }

    public void parse(final String text, final Component component) {
        List<String> list =
                Stream.of(text.split(regularExpression))
                        .map(String::trim)
                        .collect(Collectors.toList());
        list.forEach(elem -> {
            if (!elem.isBlank()) {
                var newComponent = new Composite(type);
                component.add(newComponent);
                getNextParser().parse(elem, newComponent);
            }
        });
    }
}
