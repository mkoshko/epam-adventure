package by.koshko.task03.service;

import by.koshko.task03.entity.Composite;

public class TextParser extends AbstractParser {
    private final String regex = "[\\n]+[\\s{1,4}|\\t]";

    public TextParser() {
        setType(Composite.Type.PARAGRAPH);
        setRegex(regex);
    }
}
