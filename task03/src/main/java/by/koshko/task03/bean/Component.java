package by.koshko.task03.bean;

public interface Component {

    String compose();

    default void add(Component component) {
        throw new UnsupportedOperationException();
    }

    default Component getChild(int index) {
        throw new UnsupportedOperationException();
    }

    default int size() {
        return 1;
    }

}
