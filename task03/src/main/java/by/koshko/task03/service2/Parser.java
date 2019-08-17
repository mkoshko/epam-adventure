package by.koshko.task03.service2;

import by.koshko.task03.bean.Component;

public interface Parser {
    void setNext(Parser nextParser);
    void parse(String text, Component component);
}
