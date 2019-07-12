package by.koshko.task01.service.factory;

import by.koshko.task01.entity.InternetPlan;
import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.validation.PlanParametersValidator;
import java.util.List;
import static by.koshko.task01.entity.PlanParameters.INTERNET_TRAFFIC;
import static by.koshko.task01.entity.PlanParameters.SUBSCRIPTION_FEE;
import static by.koshko.task01.entity.PlanParameters.PLAN_ID;
import static by.koshko.task01.entity.PlanParameters.PLAN_NAME;
import static by.koshko.task01.entity.PlanParameters.WITHIN_NETWORK_COST;
import static by.koshko.task01.entity.PlanParameters.OTHER_NETWORK_COST;
import static by.koshko.task01.entity.PlanParameters.ABROAD_COST;
import static by.koshko.task01.entity.PlanParameters.MEGABYTE_COST;
import static by.koshko.task01.entity.PlanParameters.SMS_COST;

public final class InternetPlanFactory {

    public InternetPlan create(final List<String> args) {
        if (PlanParametersValidator.validateInternetPlanParams(args)) {
            Plan.Builder builder = new InternetPlan.InternetPlanBuilder()
                    .internetTraffic(
                            Integer.valueOf(args.get(INTERNET_TRAFFIC)))
                    .subscription(Double.valueOf(args.get(SUBSCRIPTION_FEE)))
                    .id(Long.valueOf(args.get(PLAN_ID)))
                    .planName(args.get(PLAN_NAME))
                    .outgoingIn(Double.valueOf(args.get(WITHIN_NETWORK_COST)))
                    .outgoingOther(Double.valueOf(args.get(OTHER_NETWORK_COST)))
                    .outgoingAbroad(Double.valueOf(args.get(ABROAD_COST)))
                    .megabyteCost(Double.valueOf(args.get(MEGABYTE_COST)))
                    .smsCost(Double.valueOf(args.get(SMS_COST)));
            return ((InternetPlan.InternetPlanBuilder) builder).build();
        } else {
            return null;
        }
    }
}
