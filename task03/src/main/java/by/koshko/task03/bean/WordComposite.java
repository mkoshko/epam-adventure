package by.koshko.task03.bean;

public class WordComposite extends AbstractComposite {
    private final ComponentType type = ComponentType.WORD;

    @Override
    public ComponentType getType() {
        return type;
    }
}
