package by.koshko.task01.service.factory;

import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.exception.InvalidPlanArgumentsException;
import by.koshko.task01.service.PlanParameterSeparator;
import by.koshko.task01.service.exception.NoArgumentsException;
import by.koshko.task01.service.exception.PlanFactoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.koshko.task01.entity.PlanParameters.PLAN_TYPE;

public class PlanFactory implements Factory<Plan> {
    private static Logger logger = LogManager.getLogger("Logger");
    private static PlanFactory factory = new PlanFactory();
    private static BasicPlanFactory basicPlanFactory
            = new BasicPlanFactory();
    private static InternetPlanFactory internetPlanFactory
            = new InternetPlanFactory();
    private static SocialPlanfactory socialPlanfactory
            = new SocialPlanfactory();

    private PlanFactory() {
    }

    public static PlanFactory getInstance() {
        return factory;
    }

    public Plan create(final String params) throws PlanFactoryException {
        List<String> args = PlanParameterSeparator.separate(params);
        try {
            return create0(args);
        } catch (InvalidPlanArgumentsException e) {
            throw new PlanFactoryException(e);
        }
    }

    public List<Plan> create(final List<String> params) throws PlanFactoryException {
        List<Plan> plans = new ArrayList<>();
        for (String s : params) {
            List<String> args = PlanParameterSeparator.separate(s);
            Optional<Plan> opt = Optional.empty();
            try {
                opt = Optional.ofNullable(create0(args));
            } catch (InvalidPlanArgumentsException e) {
                logger.error("Invalid parameters " + args);
            }
            opt.ifPresent(plans::add);
        }
        return plans;
    }

    private Plan create0(final List<String> args)
            throws NoArgumentsException, InvalidPlanArgumentsException {
        if (args.size() == 0) {
            throw new NoArgumentsException();
        }
        switch (args.get(PLAN_TYPE).toUpperCase()) {
            case ("BASIC"):
                return basicPlanFactory.create(args);
            case ("INTERNET"):
                return internetPlanFactory.create(args);
            case ("SOCIAL"):
                return socialPlanfactory.create(args);
            default:
                throw new InvalidPlanArgumentsException(
                        "Wrong plan type: " + args.get(PLAN_TYPE));

        }
    }
}
