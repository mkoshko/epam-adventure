package by.koshko.task03.entity;

/**
 * Class represent the component which type is a {@link ComponentType#SYMBOL}.
 * The class used as a leaf of a tree.
 */
public class Symbol implements Component {
    /**
     * Indicates what type of component it is.
     */
    private final ComponentType type = ComponentType.SYMBOL;
    /**
     * Holds a character.
     */
    private char ch;

    /**
     * Creates {@code Symbol} objects and saves specified character argument.
     *
     * @param character to be saved in internal field.
     */
    public Symbol(final char character) {
        ch = character;
    }

    /**
     * Returns type of {@code Component}.
     *
     * @return type of this {@code Component} object.
     */
    @Override
    public ComponentType getType() {
        return type;
    }

    /**
     * Returns character as a string.
     *
     * @return character as a string.
     */
    public String compose() {
        return String.valueOf(ch);
    }
}
