package by.koshko.task03.entity;

public class LexemeComposite extends AbstractComposite {
    private final ComponentType type = ComponentType.LEXEME;

    @Override
    public ComponentType getType() {
        return type;
    }
}
