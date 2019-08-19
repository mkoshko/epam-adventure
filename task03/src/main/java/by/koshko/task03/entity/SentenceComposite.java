package by.koshko.task03.entity;

import java.util.StringJoiner;

public class SentenceComposite extends AbstractComposite {
    private static final String DELIMITER = "\u0020";

    public SentenceComposite() {
        type = ComponentType.SENTENCE;
    }

    @Override
    public String compose() {
        var joiner = new StringJoiner(DELIMITER);
        getComponents().forEach(component -> joiner.add(component.compose()));
        return joiner.toString();
    }
}
