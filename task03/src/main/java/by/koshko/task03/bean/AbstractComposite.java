package by.koshko.task03.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public abstract class AbstractComposite implements Component {
    private static final String EMPTY_DELIMITER = "";
    protected Logger logger = LogManager.getLogger(getClass());
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
