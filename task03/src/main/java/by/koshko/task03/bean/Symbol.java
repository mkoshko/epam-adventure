package by.koshko.task03.bean;

public class Symbol implements Component {
    private final ComponentType type = ComponentType.SYMBOL;
    private char ch;

    public Symbol(final char character) {
        ch = character;
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    public String compose() {
        return String.valueOf(ch);
    }
}
