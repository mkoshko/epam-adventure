package by.koshko.task03.service;

import java.util.LinkedList;
import java.util.List;

public class Word implements Composite {
    private List<Component> characters = new LinkedList<>();

    public Word(final String word) {
        for (char ch : WordParser.parse(word)) {
            characters.add(new LeafCharacter(ch));
        }
    }

    @Override
    public List<Component> getChildren() {
        return characters;
    }

    private static class WordParser {
        static char[] parse(String word) {
            return word.toCharArray();
        }
    }
}
