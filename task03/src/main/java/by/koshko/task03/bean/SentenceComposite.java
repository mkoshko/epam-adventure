package by.koshko.task03.bean;

public class SentenceComposite extends AbstractComposite {
    private static final String DELIMITER = "\u0020";
    private final ComponentType type = ComponentType.SENTENCE;

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public String compose() {
        var builder = new StringBuilder();
        getComponents().forEach(component -> {
            var str = component.compose();
            if (str.contains("\n")) {
                builder.append(str);
            } else {
                builder.append(DELIMITER).append(str);
            }
        });
        return builder.toString();
    }
}
