package by.koshko.task03.entity;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AbstractCompositeTest {

    TextComposite textComposite = new TextComposite();

    @Test
    public void testAdd() {
        textComposite.add(null);
    }

    @Test
    public void testRemove() {
        textComposite.remove(null);
    }

    @Test
    public void getChildTest() {
        textComposite.getChild(0);
    }
}