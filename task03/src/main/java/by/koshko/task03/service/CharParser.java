package by.koshko.task03.service;

import by.koshko.task03.entity.Character;
import by.koshko.task03.entity.Component;

import java.util.stream.IntStream;

public class CharParser extends AbstractParser {
    @Override
    public void parse(final String text, final Component component) {
        char[] chars = text.toCharArray();
        IntStream.range(0, chars.length)
                .forEach(i -> component.add(new Character(chars[i])));
    }
}
