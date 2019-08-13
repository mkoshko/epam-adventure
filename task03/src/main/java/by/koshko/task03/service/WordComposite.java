package by.koshko.task03.service;

import java.util.LinkedList;
import java.util.List;

public class WordComposite implements Composite {
    private List<ComponentCharacter> characters = new LinkedList<>();

    public WordComposite(final String str) {
        for (char ch : WordParser.parse(str)) {
            characters.add(new ComponentCharacter(ch));
        }
    }

    private static class WordParser {
        static char[] parse(String str) {
            return str.toCharArray();
        }
    }
}
