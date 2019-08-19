package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.ComponentType;

import java.util.Comparator;
import java.util.List;

import static by.koshko.task03.entity.ComponentType.LEXEME;
import static by.koshko.task03.entity.ComponentType.PARAGRAPH;
import static by.koshko.task03.entity.ComponentType.SENTENCE;
import static by.koshko.task03.entity.ComponentType.WORD;


public class SortingService {

    private Component text;

    public SortingService(final Component component) throws ServiceException {
        text = component;
        if (text.getType() != ComponentType.TEXT) {
            throw new ServiceException("Wrong component type. 'TEXT' needed.");
        }
    }

    /**
     * Sort paragraphs by number of sentences.
     */
    public void sort0() {
        List<Component> paragraphs = MonkeyService.obtain(text, PARAGRAPH);
        paragraphs.sort(Comparator.comparingInt(Component::size));
        text.removeAll();
        text.addAll(paragraphs);
    }

    /**
     * Sort words in sentences by length.
     */
    public void sort1() {
        List<Component> sentences = MonkeyService.obtain(text, SENTENCE);
        sentences.forEach(sentence -> {
            List<Component> lexemes = MonkeyService.obtain(sentence, LEXEME);
            lexemes.sort(Comparator.comparingInt(value -> {
                int length = 0;
                for (int i = 0; i < value.size(); i++) {
                    if (value.getChild(i).getType() == WORD) {
                        length += value.getChild(i).size();
                    }
                }
                return length;
            }));
            sentence.removeAll();
            sentence.addAll(lexemes);
        });
    }

    /**
     * Sort sentences by number of words.
     */
    public void sort2() {
        List<Component> paragraphs = MonkeyService.obtain(text, PARAGRAPH);
        paragraphs.forEach(paragraph -> {
            List<Component> sentences = MonkeyService.obtain(paragraph,
                                                             SENTENCE);
            sentences.sort(Comparator.comparingInt(Component::size));
            paragraph.removeAll();
            paragraph.addAll(sentences);
        });
    }

    //Отсортировать лексемы в тексте по убыванию количества вхождений заданного
    //символа, а в случае равенства – по алфавиту.
    public void sort3(final char ch) {
        List<Component> sentences = MonkeyService.obtain(text, SENTENCE);
        sentences.forEach(sentence -> {
            List<Component> lexemes = MonkeyService.obtain(sentence, LEXEME);
            lexemes.sort(Comparator.comparing(Component::compose,
                    Comparator.comparingInt(s -> {
                        int number = 0;
                        for (char c : s.toCharArray()) {
                            if (ch == c) {
                                number++;
                            }
                        }
                        return number;
                    })).reversed().thenComparing(Component::compose));
            sentence.removeAll();
            sentence.addAll(lexemes);
        });
    }
}
