package by.koshko.task01.service.factory;

import by.koshko.task01.entity.BasicPlan;
import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.exception.PlanFactoryException;
import by.koshko.task01.service.validation.PlanParameterValidator;

import java.util.List;

import static by.koshko.task01.entity.PlanParameters.PLAN_ID;
import static by.koshko.task01.entity.PlanParameters.PLAN_NAME;
import static by.koshko.task01.entity.PlanParameters.WITHIN_NETWORK_COST;
import static by.koshko.task01.entity.PlanParameters.OTHER_NETWORK_COST;
import static by.koshko.task01.entity.PlanParameters.ABROAD_COST;
import static by.koshko.task01.entity.PlanParameters.MEGABYTE_COST;
import static by.koshko.task01.entity.PlanParameters.SMS_COST;
import static by.koshko.task01.entity.PlanParameters.MINS_FAVOURITE_NUMBER;
import static by.koshko.task01.entity.PlanParameters.SMS_TO_FAVOURITE_NUMS;
import static by.koshko.task01.entity.PlanParameters.FAVOURITE_NUMBER;

public final class BasicPlanFactory {

    /**
     * Creates {@code BasicPlan} object.
     *
     * @param args {@code List} contains parameters required
     *             for object creation.
     * @return {@code BasicPlan} object.
     * @throws PlanFactoryException if parameters validation is failed.
     */
    public BasicPlan create(final List<String> args)
            throws PlanFactoryException {
        if (PlanParameterValidator.validateBasicPlanParams(args)) {
            Plan.Builder builder = new BasicPlan.BasicPlanBuilder()
                    .minutesFavouriteNumber(
                            Integer.valueOf(args.get(MINS_FAVOURITE_NUMBER)))
                    .smsFavouriteNumber(
                            Integer.valueOf(args.get(SMS_TO_FAVOURITE_NUMS)))
                    .favouriteNumbers(
                            Integer.valueOf(args.get(FAVOURITE_NUMBER)))
                    .id(Long.valueOf(args.get(PLAN_ID)))
                    .planName(args.get(PLAN_NAME))
                    .outgoingIn(Double.valueOf(args.get(WITHIN_NETWORK_COST)))
                    .outgoingOther(Double.valueOf(args.get(OTHER_NETWORK_COST)))
                    .outgoingAbroad(Double.valueOf(args.get(ABROAD_COST)))
                    .megabyteCost(Double.valueOf(args.get(MEGABYTE_COST)))
                    .smsCost(Double.valueOf(args.get(SMS_COST)));
            return ((BasicPlan.BasicPlanBuilder) builder).build();
        } else {
            throw new PlanFactoryException("Invalid parameters");
        }
    }

}
