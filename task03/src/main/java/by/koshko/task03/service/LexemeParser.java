package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.Composite;

public class LexemeParser extends AbstractParser {
    private String regex = "(?<=\\W)(?=\\w)|(?<=\\w)(?=\\W)";

    @Override
    public void parse(final String text, final Component component) {
        String[] s = text.split(regex);
        for (String value : s) {
            if (value.matches("\\w+")) {
                var newComponent = new Composite(Composite.Type.WORD);
                component.add(newComponent);
                getNextParser().parse(value, newComponent);
            } else if (!value.isBlank() || value.matches("\\n")) {
                var newComponent = new Composite(Composite.Type.MARK);
                component.add(newComponent);
                getNextParser().parse(value, newComponent);
            }
        }
    }
}
