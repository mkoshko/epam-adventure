package by.koshko.task03.entity;

import java.util.List;

public interface Component {

    default void add(Component component) {
        throw new UnsupportedOperationException();
    }
    default void addAll(List<Component> components) {
        throw new UnsupportedOperationException();
    }

    default void remove(Component component) {
        throw new UnsupportedOperationException();
    }
    default void removeAll() {
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
