package by.koshko.task03.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Composite implements Component {
    private Logger logger = LogManager.getLogger(getClass());
    private List<Component> components = new ArrayList<>();
    private Type componentType;

    public Composite(final Type type) {
        componentType = type;
    }

    public Type getComponentType() {
        return componentType;
    }

    @Override
    public void add(final Component component) {
        if (component != null) {
            components.add(component);
        } else {
            logger.warn("Attempt to add null into component list");
        }
    }

    @Override
    public Component getChild(final int index) {
        return components.get(index);
    }

    @Override
    public String compose() {
        if (componentType == Type.PARAGRAPH || componentType == Type.SENTENCE) {
            StringJoiner join = new StringJoiner(componentType.getDelimiter());
            components.forEach(c -> join.add(c.compose()));
            return join.toString();
        } else {
            StringBuilder builder = new StringBuilder();
            components.forEach(c -> builder
                    .append(componentType.getDelimiter())
                    .append(c.compose()));
            return builder.toString();
        }
    }

    @Override
    public int size() {
        return components.size();
    }

    public enum Type {
        TEXT("\n\t"),
        PARAGRAPH(" "),
        SENTENCE(" "),
        LEXEME(""),
        WORD(""),
        MARK("");

        Type(final String delimiter) {
            componentDelimiter = delimiter;
        }

        private String componentDelimiter;

        public String getDelimiter() {
            return componentDelimiter;
        }
    }
}
