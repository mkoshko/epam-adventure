package by.koshko.task01.dao.repository;

import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.specification.FindBySpecification;
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

    @Override
    public boolean add(final Plan plan) {
        return DATABASE.add(plan);
    }

    @Override
    public boolean remove(final Plan plan) {
        return false;
    }

    @Override
    public List<Plan> query(final Specification<Plan> specification) {
        List<Plan> list = new ArrayList<>();
        if (specification instanceof FindBySpecification) {
            FindBySpecification spec = (FindBySpecification) specification;
            for (Plan plan : DATABASE) {
                if (spec.isSatisfiedBy(plan)) {
                    list.add(plan);
                }
            }
        }
        return list;
    }
}
