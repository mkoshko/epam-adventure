package by.koshko.task03.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractComposite implements Component {
    protected Logger logger = LogManager.getLogger(getClass());
    protected List<Component> components = new ArrayList<>();

    @Override
    public void add(final Component component) {
        component.add(component);
    }

    public Component getChild(final int index) {
        return components.get(index);
    }

    @Override
    public int size() {
        return components.size();
    }
}
