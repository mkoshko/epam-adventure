package by.koshko.task03.entity;

public interface Component {
    default void add(Component component) {
        throw new UnsupportedOperationException();
    }

    default void remove(Component component) {
        throw new UnsupportedOperationException();
    }

    default Component getChild(int index) {
        throw new UnsupportedOperationException();
    }

    String compose();

    default int size() {
        return 1;
    }


}
