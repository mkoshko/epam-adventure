package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.Composite;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LexemeParser extends AbstractParser {
    private String regex = "(?<=\\W)(?=\\w)|(?<=\\w)(?=\\W)";


    @Override
    public void parse(final String text, final Component component) {
        String[] s = text.split(regex);
        System.out.println(Arrays.toString(s));
        for (int i = 0; i < s.length; i++) {
            if (s[i].matches("\\w+")) {
                var newComponent = new Composite(Composite.Type.WORD);
                component.add(newComponent);
                getNextParser().parse(s[i], newComponent);
            } else if (!s[i].isBlank()) {
                var newComponent = new Composite(Composite.Type.MARK);
                component.add(newComponent);
                getNextParser().parse(s[i], newComponent);
            }
        }
    }
}
