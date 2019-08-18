package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.ComponentType;

import java.util.Comparator;
import java.util.stream.Stream;


public class SortingService {

    private Component text;

    public SortingService(final Component component) {
        text = component;
        if (text.getType() != ComponentType.TEXT) {
            throw new RuntimeException();
        }
    }

    /**
     * Sort paragraphs by number of sentences.
     */
    public void sort0() {
        var paragraphs = MonkeyService.obtain(text, ComponentType.PARAGRAPH);
        paragraphs.sort(Comparator.comparingInt(Component::size));
        text.removeAll();
        text.addAll(paragraphs);
    }

    /**
     * Sort words in sentences by word length.
     */
    public void sort1() {
        var sentences = MonkeyService.obtain(text, ComponentType.SENTENCE);
        sentences.forEach(sentence -> {
            var lexemes = MonkeyService.obtain(sentence, ComponentType.LEXEME);
            lexemes.sort(Comparator.comparingInt(value -> {
                var length = 0;
                for (int i = 0; i < value.size(); i++) {
                    if (value.getChild(i).getType() == ComponentType.WORD) {
                        length += value.getChild(i).size();
                    }
                }
                return length;
            }));
            sentence.removeAll();
            sentence.addAll(lexemes);
        });
    }
}
