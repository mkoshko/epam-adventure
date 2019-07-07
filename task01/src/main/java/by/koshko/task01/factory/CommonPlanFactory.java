package by.koshko.task01.factory;

import by.koshko.task01.entity.CommonPlan;

import java.util.List;

public final class CommonPlanFactory implements Factory<CommonPlan> {

    @Override
    public CommonPlan createInstance(final List<String> params) {
        int i = 1;
        CommonPlan plan = new CommonPlan(
                params.get(i++).trim(),
                Double.valueOf(params.get(i++).trim()),
                Double.valueOf(params.get(i++).trim()),
                Double.valueOf(params.get(i).trim())
        );
        return plan;
    }

}
