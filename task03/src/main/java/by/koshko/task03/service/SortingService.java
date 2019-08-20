package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.ComponentType;

import java.util.Comparator;
import java.util.List;

import static by.koshko.task03.entity.ComponentType.LEXEME;
import static by.koshko.task03.entity.ComponentType.PARAGRAPH;
import static by.koshko.task03.entity.ComponentType.SENTENCE;
import static by.koshko.task03.entity.ComponentType.WORD;

/**
 * Class contains static methods for sorting components in
 * {@code TextComposite}.
 *
 * @see #sortBySentencesNumber(Component).
 * @see #sortByWordsLength(Component).
 * @see #sortByWordsNumber(Component).
 * @see #sortByCharsNumber(Component, char).
 */
public final class SortingService {

    /**
     * Private constructor.
     */
    private SortingService() {
    }

    /**
     * Sort paragraphs by number of sentences.
     *
     * @param text Text component to be sorted.
     * @throws ServiceException if component type is wrong.
     */
    public static void sortBySentencesNumber(final Component text)
            throws ServiceException {
        checkType(text);
        List<Component> paragraphs = MonkeyService.obtain(text, PARAGRAPH);
        paragraphs.sort(Comparator.comparingInt(Component::size));
        text.removeAll();
        text.addAll(paragraphs);
    }

    /**
     * Sort words in sentences by length.
     *
     * @param text Text component to be sorted.
     * @throws ServiceException if component type is wrong.
     */
    public static void sortByWordsLength(final Component text)
            throws ServiceException {
        checkType(text);
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
     *
     * @param text Text component to be sorted.
     * @throws ServiceException if component type is wrong.
     */
    public static void sortByWordsNumber(final Component text)
            throws ServiceException {
        checkType(text);
        List<Component> paragraphs = MonkeyService.obtain(text, PARAGRAPH);
        paragraphs.forEach(paragraph -> {
            List<Component> sentences = MonkeyService.obtain(paragraph,
                    SENTENCE);
            sentences.sort(Comparator.comparingInt(Component::size));
            paragraph.removeAll();
            paragraph.addAll(sentences);
        });
    }

    /**
     * Sorts lexemes by number of specific char in descending order, if number
     * is equals, then sort lexicographically.
     *
     * @param text      Text component to be sorted.
     * @param character The character by the number of occurrences of which
     *                  lexemes will be sorted.
     * @throws ServiceException if component type is wrong.
     */
    public static void sortByCharsNumber(final Component text,
                                         final char character)
            throws ServiceException {
        checkType(text);
        List<Component> sentences = MonkeyService.obtain(text, SENTENCE);
        sentences.forEach(sentence -> {
            List<Component> lexemes = MonkeyService.obtain(sentence, LEXEME);
            lexemes.sort(Comparator.comparing(Component::compose,
                    Comparator.comparingInt(s -> {
                        int number = 0;
                        for (char c : s.toCharArray()) {
                            if (character == c) {
                                number++;
                            }
                        }
                        return number;
                        //ascending order, for descending call reversed() before
                        // thenComparing()
                    })).thenComparing(Component::compose).reversed());
            sentence.removeAll();
            sentence.addAll(lexemes);
        });
    }

    /**
     * Check if component type is correct.
     *
     * @param component component which type will be checked.
     * @throws ServiceException if component type is wrong.
     */
    private static void checkType(final Component component)
            throws ServiceException {
        if (component == null) {
            throw new ServiceException("Component is null.");
        }
        if (component.getType() != ComponentType.TEXT) {
            throw new ServiceException("Wrong component type. 'TEXT' needed.");
        }
    }
}
