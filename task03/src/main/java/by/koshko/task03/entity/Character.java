package by.koshko.task03.entity;

public class Character implements Component {
    private char ch;

    public Character(final char character) {
        ch = character;
    }

    @Override
    public String compose() {
        return String.valueOf(ch);
    }
}
