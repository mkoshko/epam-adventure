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
        var size = paragraphs.size();
        for (int i = 0; i < size; i++) {
            text.remove(text.getChild(0));
        }
        paragraphs.forEach(text::add);
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
            var size = lexemes.size();
            for (int i = 0; i < size; i++) {
                sentence.remove(sentence.getChild(0));
            }
            lexemes.forEach(sentence::add);
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
            var size = paragraph.size();
            for (int i = 0; i < size; i++) {
                paragraph.remove(paragraph.getChild(0));
            }
            sentences.forEach(paragraph::add);
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
                    })).reversed().thenComparing(Component::compose));
            var size = sentence.size();
            for (int i = 0; i < size; i++) {
                sentence.remove(sentence.getChild(0));
            }
            lexemes.forEach(sentence::add);
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
