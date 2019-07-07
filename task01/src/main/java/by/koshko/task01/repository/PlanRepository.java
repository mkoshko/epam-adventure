package by.koshko.task01.repository;

import by.koshko.task01.entity.CommonPlan;

import java.util.ArrayList;
import java.util.List;

public class PlanRepository implements Repository<CommonPlan> {

    private static final String PLAN_NAME_ERROR_MESSAGE = "Plan name expected not null";
    private static final List<CommonPlan> PLAN_DATABASE = new ArrayList<>();

    @Override
    public boolean add(final CommonPlan plan) {
        for (CommonPlan p : PLAN_DATABASE) {
            if (p.getPlanName().equals(plan.getPlanName())) {
                return false;
            }
        }
        return PLAN_DATABASE.add(plan);
    }

    @Override
    public boolean remove(final String planName) {
        if (planName == null) {
            throw new NullPointerException(PLAN_NAME_ERROR_MESSAGE);
        }
        for (CommonPlan p : PLAN_DATABASE) {
            if (p.getPlanName().equals(planName)) {
                return PLAN_DATABASE.remove(p);
            }
        }
        return false;
    }

    @Override
    public CommonPlan findByName(final String planName) {
        if (planName == null) {
            throw new NullPointerException(PLAN_NAME_ERROR_MESSAGE);
        }
        for (CommonPlan p : PLAN_DATABASE) {
            if (p.getPlanName().equals(planName)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<CommonPlan> getAll() {
        return PLAN_DATABASE;
    }
}
