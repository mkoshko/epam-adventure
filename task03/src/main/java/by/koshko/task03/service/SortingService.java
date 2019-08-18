package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.ComponentType;

import java.util.Comparator;


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

}
