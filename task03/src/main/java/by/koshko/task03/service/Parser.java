package by.koshko.task03.service;

import by.koshko.task03.entity.Component;

public interface Parser {
    void setNext(Parser nextParser);
    void parse(String text, Component component);
}
