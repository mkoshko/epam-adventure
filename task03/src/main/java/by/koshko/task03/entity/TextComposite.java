package by.koshko.task03.entity;

import java.util.StringJoiner;

public class TextComposite extends AbstractComposite {
    private final ComponentType type = ComponentType.TEXT;
    private static final String DELIMITER = "\n\t";

    public ComponentType getType() {
        return type;
    }

    @Override
    public String compose() {
        var joiner = new StringJoiner(DELIMITER);
        getComponents().forEach(component -> joiner.add(component.compose()));
        return "\t" + joiner.toString();
    }
}
