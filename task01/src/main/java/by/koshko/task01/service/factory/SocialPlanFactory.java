package by.koshko.task01.service.factory;

import by.koshko.task01.entity.Plan;
import by.koshko.task01.entity.SocialPlan;
import by.koshko.task01.service.exception.PlanFactoryException;
import by.koshko.task01.service.validation.PlanParameterValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import static by.koshko.task01.entity.PlanParameters.SUBSCRIPTION_FEE;
import static by.koshko.task01.entity.PlanParameters.PLAN_ID;
import static by.koshko.task01.entity.PlanParameters.PLAN_NAME;
import static by.koshko.task01.entity.PlanParameters.WITHIN_NETWORK_COST;
import static by.koshko.task01.entity.PlanParameters.OTHER_NETWORK_COST;
import static by.koshko.task01.entity.PlanParameters.ABROAD_COST;
import static by.koshko.task01.entity.PlanParameters.MEGABYTE_COST;
import static by.koshko.task01.entity.PlanParameters.SMS_COST;
import static by.koshko.task01.entity.PlanParameters.MINUTES_IN_NETWORK;
import static by.koshko.task01.entity.PlanParameters.MINUTES_OTHER_NETWORK;
import static by.koshko.task01.entity.PlanParameters.FREE_SMS;

public final class SocialPlanFactory {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger(this);
    /**
     * Creates {@code SocialPlan} object.
     *
     * @param args {@code List} contains parameters required
     *             for object creation.
     * @return {@code SocialPlan} object.
     * @throws PlanFactoryException
     *         if parameters validation is failed.
     */
    public SocialPlan create(final List<String> args)
            throws PlanFactoryException {
        if (PlanParameterValidator.validateSocialPlanParams(args)) {
            Plan.Builder builder = new SocialPlan.SocialPlanBuilder()
                    .minutesIn(Integer.valueOf(args.get(MINUTES_IN_NETWORK)))
                    .minutesOther(
                            Integer.valueOf(args.get(MINUTES_OTHER_NETWORK)))
                    .freeSms(Integer.valueOf(args.get(FREE_SMS)))
                    .subscription(Double.valueOf(args.get(SUBSCRIPTION_FEE)))
                    .id(Long.valueOf(args.get(PLAN_ID)))
                    .planName(args.get(PLAN_NAME))
                    .outgoingIn(Double.valueOf(args.get(WITHIN_NETWORK_COST)))
                    .outgoingOther(Double.valueOf(args.get(OTHER_NETWORK_COST)))
                    .outgoingAbroad(Double.valueOf(args.get(ABROAD_COST)))
                    .megabyteCost(Double.valueOf(args.get(MEGABYTE_COST)))
                    .smsCost(Double.valueOf(args.get(SMS_COST)));
            logger.info("Creating plan with parameters: " + args);
            return ((SocialPlan.SocialPlanBuilder) builder).build();
        } else {
            logger.info("Invalid parameters: " + args);
            throw new PlanFactoryException("Invalid parameters");
        }
    }

}
