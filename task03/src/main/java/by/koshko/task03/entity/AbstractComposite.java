package by.koshko.task03.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Class contains all basic methods realizations of {@code Component} interface.
 */
public abstract class AbstractComposite implements Component {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger(getClass());
    /**
     * Indicates what type of component it is.
     */
    private ComponentType type;
    /**
     * Default delimiter for components. Might be changed in subclasses.
     * Using in {@link #compose()} method for append components with each other.
     */
    private static final String EMPTY_DELIMITER = "";
    /**
     * List of {@code Component} objects.
     */
    private List<Component> components = new ArrayList<>();

    /**
     * Returns list of {@code Component} objects. Using in subclasses.
     *
     * @return list of {@code Component} objects.
     */
    protected List<Component> getComponents() {
        return components;
    }

    /**
     * Adds component to {@link #components} list.
     *
     * @param component {@code Component} to be added to {@link #components}.
     */
    @Override
    public void add(final Component component) {
        if (component != null) {
            components.add(component);
        } else {
            logger.info("Attempt to add null component");
        }
    }

    /**
     * Removes component from {@link #components} list if such exists.
     *
     * @param component {@code Component} to be removed.
     */
    @Override
    public void remove(final Component component) {
        if (component != null) {
            components.remove(component);
        } else {
            logger.info("Attempt to remove null component");
        }
    }

    /**
     * Returns the {@code Component} object at the specified position from
     * {@link #components} list.
     *
     * @param index index of the element to return.
     * @return the element at the specified position in this list.
     * @throws IndexOutOfBoundsException if the index is out of range.
     *                                   ({@code index < 0 || index >= size()}).
     */
    @Override
    public Component getChild(final int index) {
        return components.get(index);
    }

    /**
     * Returns size of {@link #components} list.
     *
     * @return size of {@link #components} list.
     */
    @Override
    public int size() {
        return components.size();
    }

    /**
     * Sets the type of {@code Component}. The method using only in subclasses.
     *
     * @param componentType type to be set to {@link #type}.
     */
    protected void setType(final ComponentType componentType) {
        type = componentType;
    }

    /**
     * Returns type of {@code Component}. The method using only in subclasses.
     *
     * @return type of this {@code Component} object.
     */
    @Override
    public ComponentType getType() {
        return type;
    }

    /**
     * Returns string representation of this {@code Component}.
     *
     * @return string representation of this {@code Component}.
     */
    @Override
    public String compose() {
        var joiner = new StringJoiner(EMPTY_DELIMITER);
        components.forEach(component -> joiner.add(component.compose()));
        return joiner.toString();
    }
}
