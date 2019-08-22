package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.ComponentType;

import java.util.ArrayList;
import java.util.List;

import static by.koshko.task03.entity.ComponentType.SYMBOL;

/**
 * Service which allows to get all components with specified type from given
 * component.
 */
public final class MonkeyService {
    /**
     * Private constructor.
     */
    private MonkeyService() {
    }

    /**
     * Collects all component with specified type argument, from given component
     * to a list.
     * @param component Component from where components with specified types
     *                  will be collected.
     * @param type Component type which will be collected.
     * @return list of collected components obtained from given component.
     */
    public static List<Component> obtain(final Component component,
                                         final ComponentType type) {
        var components = new ArrayList<Component>();
        collectComponents(component, type, components);
        return components;
    }

    /**
     * Recursive method for searching and adding components with specified type
     * to a list.
     * @param component Component from where specified components will be
     *                  collecting.
     * @param type Type of component to be collected.
     * @param components list in which specific type components will be added.
     */
    private static void collectComponents(final Component component,
                             final ComponentType type,
                             final List<Component> components) {
        if (component.getType() == type) {
            components.add(component);
        } else {
            if (component.size() > 0 && component.getType() != SYMBOL) {
                for (int i = 0; i < component.size(); i++) {
                    collectComponents(component.getChild(i), type, components);
                }
            }
        }
    }
}
