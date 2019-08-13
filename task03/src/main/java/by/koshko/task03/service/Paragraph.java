package by.koshko.task03.service;

import java.util.LinkedList;
import java.util.List;

public class Paragraph implements Composite {
    private List<Component> sentences = new LinkedList<>();



    @Override
    public List<Component> getChildren() {
        return null;
    }
}
