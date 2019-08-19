package by.koshko.task03.service;

import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.ComponentType;

import java.util.ArrayList;
import java.util.List;

import static by.koshko.task03.entity.ComponentType.SYMBOL;

public final class MonkeyService {
    private MonkeyService() {
    }

    private static void find(final Component component,
                             final ComponentType type,
                             final List<Component> components) {
        if (component.getType() == type) {
            components.add(component);
        } else {
            if (component.size() > 0 && component.getType() != SYMBOL) {
                for (int i = 0; i < component.size(); i++) {
                    find(component.getChild(i), type, components);
                }
            }
        }
    }

    public static List<Component> obtain(final Component component,
                                         final ComponentType type) {
        var components = new ArrayList<Component>();
        find(component, type, components);
        return components;
    }
}
