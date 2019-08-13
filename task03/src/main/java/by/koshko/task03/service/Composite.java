package by.koshko.task03.service;

import java.util.List;

public interface Composite extends Component {
    List<Component> getChildren();
}
