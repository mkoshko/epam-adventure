package by.koshko.task03.entity;

/**
 * The interface represents a component that can be used as the root of a tree.
 * Contains methods {@link #add(Component)} and {@link #remove(Component)}
 * from internal list. Number of components that the root is keeps can be
 * obtain by calling {@link #size()} method. The component from specified
 * position can be obtain by calling {@link #getChild(int)} method.
 */
public interface Component {
    /**
     * Adds component to internal storage of {@code Component}.
     *
     * @param component {@code Component} to be added.
     */
    default void add(Component component) {
        throw new UnsupportedOperationException();
    }
    /**
     * Removes component from internal storage if such component exists.
     *
     * @param component {@code Component} to be removed.
     */
    default void remove(Component component) {
        throw new UnsupportedOperationException();
    }
    /**
     * Returns the {@code Component} object at the specified position from
     * {@code Component} object.
     *
     * @param index index of the element to return.
     * @return the element at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range.
     *                                   ({@code index < 0 || index >= size()}).
     */
    default Component getChild(int index) {
        throw new UnsupportedOperationException();
    }
    /**
     * Returns size of internal list.
     *
     * @return size of internal list.
     */
    default int size() {
        return 1;
    }

    /**
     * Returns string representation of this {@code Component}.
     *
     * @return string representation of this {@code Component}.
     */
    String compose();

    /**
     * Returns type of {@code Component}.
     *
     * @return type of this {@code Component} object.
     */
    ComponentType getType();

}
