package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.Composite;

public class LexemeParser extends AbstractParser {
    private String regex = "(?<=\\W)(?=\\w)|(?<=\\w)(?=\\W)";
    private Parser markParser;
    public LexemeParser(final Parser parser) {
        markParser = parser;
    }

    @Override
    public void parse(final String text, final Component component) {
        String[] s = text.split(regex);
        for (int i = 0; i < s.length; i++) {
            if (s[i].matches("\\w+")) {
                var newComponent = new Composite(Composite.Type.WORD);
                component.add(newComponent);
                getNextParser().parse(s[i], newComponent);
            } else {
                var newComponent = new Composite(Composite.Type.MARK);
                component.add(newComponent);
                markParser.parse(s[i], newComponent);
            }
        }
    }
}
