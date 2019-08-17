package by.koshko.task03.entity;

import java.util.StringJoiner;

public class ParagraphComposite extends AbstractComposite {
    private final ComponentType type = ComponentType.PARAGRAPH;
    private static final String DELIMITER = "\u0020";

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
