package by.koshko.task03.entity;

import java.util.StringJoiner;
/**
 * Class represent the component which type is a {@link ComponentType#SENTENCE}.
 */
public class SentenceComposite extends AbstractComposite {
    /**
     * Delimiter using in {@link #compose()} methods.
     */
    private static final String DELIMITER = "\u0020";
    /**
     * Creates an {@code SentenceComposite} objects and sets it's type.
     */
    public SentenceComposite() {
        setType(ComponentType.SENTENCE);
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
        return joiner.toString();
    }
}
