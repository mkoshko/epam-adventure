package by.koshko.task03.entity;

public class MarkComposite extends AbstractComposite {
    private final ComponentType type = ComponentType.MARK;

    @Override
    public ComponentType getType() {
        return type;
    }
}
