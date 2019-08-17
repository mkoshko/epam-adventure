package by.koshko.task03.service2;

import by.koshko.task03.bean.Component;
import by.koshko.task03.bean.Symbol;

import java.util.stream.IntStream;

public class SymbolParser implements Parser {
    @Override
    public void setNext(final Parser nextParser) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void parse(final String text, final Component component) {
        char[] chars = text.toCharArray();
        IntStream.range(0, chars.length)
                .forEach(i -> component.add(new Symbol(chars[i])));
    }
}
