package by.koshko.task01.service.factory;

import by.koshko.task01.entity.InternetPlan;
import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.exception.PlanFactoryException;
import by.koshko.task01.service.validation.PlanParameterValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger(this);
    /**
     * Creates {@code InternetPlan} object.
     *
     * @param args {@code List} contains parameters required
     *             for object creation.
     * @return {@code InternetPlan} object.
     * @throws PlanFactoryException if parameters validation is failed.
     */
    public InternetPlan create(final List<String> args)
            throws PlanFactoryException {
        if (PlanParameterValidator.validateInternetPlanParams(args)) {
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
            logger.info("Creating plan with parameters: " + args);
            return ((InternetPlan.InternetPlanBuilder) builder).build();
        } else {
            logger.info("Invalid parameters: " + args);
            throw new PlanFactoryException("Invalid parameters");
        }
    }
}
