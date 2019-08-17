package by.koshko.task03.service;

import by.koshko.task03.entity.Composite;

public class SentenceParser extends AbstractParser {
    private final String regex = "(?<=(\\n)|(\u0020))(?=(.))";
    public SentenceParser() {
        setType(Composite.Type.LEXEME);
        setRegex(regex);
    }
}
