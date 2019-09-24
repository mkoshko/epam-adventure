package by.koshko.cyberwikia.service;

import java.util.EnumMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Predicate;

public class ValidationFactory {
    private static final ValidationFactory INSTANCE = new ValidationFactory();
    private Map<Type, Predicate<Properties>> validators
            = new EnumMap<>(Type.class);

    private ValidationFactory() {
        validators.put(Type.USER, new UserValidator());
    }

    public static ValidationFactory access() {
        return INSTANCE;
    }

    public Predicate<Properties> getValidator(final Type type) {
        return validators.get(type);
    }

    public enum Type {
        USER,
        TOURNAMENT
    }
}
