package by.koshko.task03.service;

import java.util.LinkedList;
import java.util.List;

public class Sentence implements Composite {
    List<Component> lexemes = new LinkedList<>();

    public Sentence(String sentence) {
        SentenceParser.parse(lexemes, sentence);
    }

    @Override
    public List<Component> getChildren() {
        return lexemes;
    }

    private static class SentenceParser {
        static void parse(List<Component> lexemes, String sentence) {

        }
    }
}
