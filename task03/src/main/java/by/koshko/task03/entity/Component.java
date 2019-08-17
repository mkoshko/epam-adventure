package by.koshko.task03.entity;

public interface Component {

    default void add(Component component) {
        throw new UnsupportedOperationException();
    }

    default Component getChild(int index) {
        throw new UnsupportedOperationException();
    }

    default int size() {
        return 1;
    }

    String compose();

    ComponentType getType();

}
