package by.koshko.task01.service.factory;

import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.exception.NoArgumentsException;
import by.koshko.task01.service.exception.PlanFactoryException;
import by.koshko.task01.service.exception.WrongPlanTypeException;
import by.koshko.task01.service.CheckParamsNumber;
import by.koshko.task01.service.PlanParameterSeparator;

import java.util.ArrayList;
import java.util.List;

import static by.koshko.task01.entity.PlanParameters.PLAN_TYPE;

public class PlanFactory implements Factory<Plan> {

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
        return create0(args);
    }
    //TODO test exceptions behavior!
    public List<Plan> create(final List<String> params) throws PlanFactoryException {
        List<Plan> plans = new ArrayList<>();
        for (String s : params) {
            List<String> args = PlanParameterSeparator.separate(s);
            Plan plan = create0(args);
            plans.add(plan);
        }
        return plans;
    }

    private Plan create0(final List<String> args) throws PlanFactoryException {
        if (args.size() == 0) {
            throw new NoArgumentsException();
        }
        switch (args.get(PLAN_TYPE).toUpperCase()) {
            case ("BASIC"):
                if (CheckParamsNumber.check(Plan.PlanType.BASIC, args)) {
                    return basicPlanFactory.create(args);
                } else {
                    return null;
                }
            case ("INTERNET"):
                if (CheckParamsNumber.check(Plan.PlanType.INTERNET, args)) {
                    return internetPlanFactory.create(args);
                } else {
                    return null;
                }
            case ("SOCIAL"):
                if (CheckParamsNumber.check(Plan.PlanType.SOCIAL, args)) {
                    return socialPlanfactory.create(args);
                } else {
                    return null;
                }
            default:
                throw new WrongPlanTypeException();

        }
    }
}
