package by.koshko.task01.service.factory;

import by.koshko.task01.service.exception.PlanFactoryException;

import java.util.List;

public interface Factory<T> {
    T create(String params) throws PlanFactoryException;
    List<T> create(List<String> params) throws PlanFactoryException;
}
