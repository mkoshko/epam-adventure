package by.koshko.task01.dao.repository;

import by.koshko.task01.service.specification.Specification;

import java.util.List;

public interface Repository<T> {

    boolean add(T t);

    boolean remove(T t);

    List<T> query(Specification<T> specification);
}
