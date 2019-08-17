package by.koshko.task03.bean;

public class Character implements Component {

    private char ch;

    public Character(final char character) {
        ch = character;
    }

    public String compose() {
        return String.valueOf(ch);
    }
}
