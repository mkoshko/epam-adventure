package by.koshko.task03.service;

import by.koshko.task03.entity.Component;

public interface Parser {
    void parse(String element, Component component);

    void setNext(Parser parser);
}
