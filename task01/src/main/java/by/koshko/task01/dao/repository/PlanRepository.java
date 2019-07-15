package by.koshko.task01.dao.repository;

import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.specification.FindBySpecification;
import by.koshko.task01.service.specification.SortBySpecification;
import by.koshko.task01.service.specification.Specification;

import java.util.ArrayList;
import java.util.List;

public class PlanRepository implements Repository<Plan> {
    private static final PlanRepository INSTANCE = new PlanRepository();
    private static final List<Plan> DATABASE = new ArrayList<>();

    private PlanRepository() {
    }

    public static PlanRepository accept() {
        return INSTANCE;
    }

    public boolean add(final Plan plan) {
        return DATABASE.add(plan);
    }

    public boolean add(final List<Plan> plans) {
        return DATABASE.addAll(plans);
    }

    public boolean remove(final Plan plan) {
        return DATABASE.remove(plan);
    }

    public boolean remove(final List<Plan> plans) {
        return DATABASE.removeAll(plans);
    }

    public List<Plan> getAll() {
        return DATABASE;
    }

    public List<Plan> query(final Specification<Plan> specification) {
        if (specification instanceof FindBySpecification) {
            List<Plan> list = new ArrayList<>();
            FindBySpecification spec = (FindBySpecification) specification;
            for (Plan plan : DATABASE) {
                if (spec.isSatisfiedBy(plan)) {
                    list.add(plan);
                }
            }
            return list;
        } else {
            ((SortBySpecification) specification).sort(DATABASE);
            return DATABASE;
        }
    }
}
