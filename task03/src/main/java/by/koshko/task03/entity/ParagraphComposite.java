package by.koshko.task03.entity;

import java.util.StringJoiner;

public class ParagraphComposite extends AbstractComposite {
    private static final String DELIMITER = "\u0020";

    public ParagraphComposite() {
        type = ComponentType.PARAGRAPH;
    }

    @Override
    public String compose() {
        var joiner = new StringJoiner(DELIMITER);
        getComponents().forEach(component -> joiner.add(component.compose()));
        return joiner.toString();
    }
}
