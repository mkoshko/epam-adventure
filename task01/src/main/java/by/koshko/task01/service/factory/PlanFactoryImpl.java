package by.koshko.task01.service.factory;

import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.exception.PlanFactoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

import static by.koshko.task01.entity.PlanParameters.PLAN_TYPE;

public class PlanFactoryImpl implements PlanFactory {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger("Logger");
    /**
     * {@code BasicPlanFactory} instance.
     */
    private static BasicPlanFactory basicPlanFactory
            = new BasicPlanFactory();
    /**
     * {@code InternetPlanFactory} instance.
     */
    private static InternetPlanFactory internetPlanFactory
            = new InternetPlanFactory();
    /**
     * {@code SocialPlanFactory} instance.
     */
    private static SocialPlanFactory socialPlanFactory
            = new SocialPlanFactory();

    /**
     * Creates {@code Plan} object with given parameters.
     *
     * @param params Parameters to be used for instance creation.
     * @return {@code Plan} object.
     * @throws PlanFactoryException occurs when some parameters
     *                              for plan creations is invalid.
     */
    public Plan create(final String params) throws PlanFactoryException {
        List<String> args = PlanParameterSeparator.separate(params);
        return create0(args);
    }

    /**
     * Creates as much {@code Plan} objects as specified in {@code List} with
     * parameters.
     *
     * @param params {@code List} contains strings with parameters for
     *               objects creation.
     * @return {@code List} contains all created {@code Plan} objects.
     * @throws PlanFactoryException occurs when some parameters
     *                              for plan creations is invalid.
     */
    public List<Plan> create(final List<String> params)
            throws PlanFactoryException {
        List<Plan> plans = new LinkedList<>();
        for (String s : params) {
            List<String> args = PlanParameterSeparator.separate(s);
            plans.add(create0(args));
        }
        return plans;
    }

    private Plan create0(final List<String> args) throws PlanFactoryException {
        if (args.size() == 0) {
            throw new PlanFactoryException("No arguments");
        }
        switch (args.get(PLAN_TYPE).toUpperCase()) {
            case ("BASIC"):
                return basicPlanFactory.create(args);
            case ("INTERNET"):
                return internetPlanFactory.create(args);
            case ("SOCIAL"):
                return socialPlanFactory.create(args);
            default:
                logger.info("Attempt to create plan with unknown type "
                        + "\'" + args.get(PLAN_TYPE) + "\'");
                throw new PlanFactoryException("Invalid plan type");
        }
    }
}
