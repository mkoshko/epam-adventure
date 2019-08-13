package by.koshko.task03.service;

import java.util.ArrayList;
import java.util.List;

public class Lexeme implements Composite {
    private List<Component> lexeme = new ArrayList<>();

    public Lexeme(final String lexeme) {

    }

    @Override
    public List<Component> getChildren() {
        return lexeme;
    }

    private static class LexemeParser {
        static void parse(List<Component> parts, String lexeme) {

        }
    }
}
