package by.koshko.task03.bean;

public class MarkComposite extends AbstractComposite {
    private final ComponentType type = ComponentType.MARK;

    @Override
    public ComponentType getType() {
        return type;
    }
}
