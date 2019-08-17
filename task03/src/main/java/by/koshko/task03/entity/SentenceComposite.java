package by.koshko.task03.entity;

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
            if (builder.toString().lastIndexOf('\n') == builder.length() - 1) {
                builder.append(component.compose());
            } else {
                builder.append(DELIMITER).append(component.compose());
            }
        });
        return builder.toString();
    }
}
