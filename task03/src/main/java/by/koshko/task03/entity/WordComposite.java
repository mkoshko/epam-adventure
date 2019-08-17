package by.koshko.task03.entity;

public class WordComposite extends AbstractComposite {
    private final ComponentType type = ComponentType.WORD;

    @Override
    public ComponentType getType() {
        return type;
    }
}
