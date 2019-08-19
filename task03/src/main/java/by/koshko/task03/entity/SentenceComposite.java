package by.koshko.task03.entity;

import java.util.StringJoiner;

public class SentenceComposite extends AbstractComposite {
    private static final String DELIMITER = "\u0020";
    private final ComponentType type = ComponentType.SENTENCE;

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public String compose() {
        var joiner = new StringJoiner(DELIMITER);
        getComponents().forEach(component -> joiner.add(component.compose()));
        return joiner.toString();
    }
}
