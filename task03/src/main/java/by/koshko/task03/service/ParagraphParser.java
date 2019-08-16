package by.koshko.task03.service;

import by.koshko.task03.entity.Composite;

public class ParagraphParser extends AbstractParser {
    private final String regex = "(?<=[.])\\s+(?=[A-Z]|[(])";

    public ParagraphParser() {
        setType(Composite.Type.SENTENCE);
        setRegex(regex);
    }
}
