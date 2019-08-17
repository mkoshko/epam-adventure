package by.koshko.task03.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public abstract class AbstractComposite implements Component {
    private static final String EMPTY_DELIMITER = "";
    private List<Component> components = new ArrayList<>();

    protected List<Component> getComponents() {
        return components;
    }

    @Override
    public void add(final Component component) {
        components.add(component);
    }

    @Override
    public Component getChild(final int index) {
        return components.get(index);
    }

    @Override
    public int size() {
        return components.size();
    }

    @Override
    public String compose() {
        var joiner = new StringJoiner(EMPTY_DELIMITER);
        components.forEach(component -> joiner.add(component.compose()));
        return joiner.toString();
    }
}
