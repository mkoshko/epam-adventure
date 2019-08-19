package by.koshko.task03.entity;

import java.util.StringJoiner;

public class TextComposite extends AbstractComposite {
    private static final String DELIMITER = "\n\t";

    public TextComposite() {
        type = ComponentType.TEXT;
    }

    @Override
    public String compose() {
        var joiner = new StringJoiner(DELIMITER);
        getComponents().forEach(component -> joiner.add(component.compose()));
        return "\t" + joiner.toString();
    }
}
