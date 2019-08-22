package by.koshko.task03.entity;

import java.util.StringJoiner;
/**
 * Class represent the component which type is a {@link ComponentType#TEXT}.
 */
public class TextComposite extends AbstractComposite {
    /**
     * Delimiter using in {@link #compose()} methods.
     */
    private static final String DELIMITER = "\n\t";

    /**
     * Creates an {@code TextComposite} objects and sets it's type.
     */
    public TextComposite() {
        setType(ComponentType.TEXT);
    }

    /**
     * Returns string representation of this {@code Component}.
     *
     * @return string representation of this {@code Component}.
     */
    @Override
    public String compose() {
        var joiner = new StringJoiner(DELIMITER);
        getComponents().forEach(component -> joiner.add(component.compose()));
        return "\t" + joiner.toString();
    }
}
