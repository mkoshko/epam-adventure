package by.koshko.task01.factory;

import by.koshko.task01.entity.CommonPlan;
import by.koshko.task01.entity.InternetPlan;
import by.koshko.task01.entity.SocialPlan;
import by.koshko.task01.exception.IllegalPlanParametersExceptions;
import by.koshko.task01.utils.Logger;
import by.koshko.task01.utils.PlanParameterSeparator;
import by.koshko.task01.validation.PlanParametersValidator;

import java.util.ArrayList;
import java.util.List;

public final class PlanFactory {
    private static final int PLAN_TYPE = 0;
    private static final String ARGS_ERROR_MESSAGE = "Wrong numbers of parameters";
    private PlanFactory() {
    }

    public static List<CommonPlan> createPlans(final List<String> params) {
        List<CommonPlan> createdPlans = new ArrayList<>();
        for (String param : params) {
            List<String> args;
            try {
                args = PlanParameterSeparator
                        .separate(param);
            } catch (IllegalPlanParametersExceptions e) {
                e.printStackTrace();
                continue;
            }
            switch (args.get(PLAN_TYPE).toUpperCase()) {
                case "COMMON":
                    if (args.size() != CommonPlan.REQUIRED_PARAMETERS + 1) {
                        Logger.getLogger().error(ARGS_ERROR_MESSAGE + " for common plan");
                        break;
                    }
                    try {
                        PlanParametersValidator.validateParamsForCommonPlan(args);
                    } catch (IllegalPlanParametersExceptions illegalPlanParametersExceptions) {
                        illegalPlanParametersExceptions.printStackTrace();
                        break;
                    }
                    createdPlans
                            .add(new CommonPlanFactory()
                                    .createInstance(args));
                    break;
                case "INTERNET":
                    if (args.size() != InternetPlan.REQUIRED_PARAMETERS + 1) {
                        Logger.getLogger().error(ARGS_ERROR_MESSAGE + " for internet plan");
                        break;
                    }
                    try {
                        PlanParametersValidator.validateParamsForInternetPlan(args);
                    } catch (IllegalPlanParametersExceptions illegalPlanParametersExceptions) {
                        illegalPlanParametersExceptions.printStackTrace();
                        break;
                    }
                    createdPlans
                            .add(new InternetPlanFactory()
                                    .createInstance(args));
                    break;
                case "SOCIAL":
                    if (args.size() != SocialPlan.REQUIRED_PARAMETERS + 1) {
                        Logger.getLogger().error(ARGS_ERROR_MESSAGE + " for social plan");
                        break;
                    }
                    try {
                        PlanParametersValidator.validateParamsForSocialPlan(args);
                    } catch (IllegalPlanParametersExceptions illegalPlanParametersExceptions) {
                        illegalPlanParametersExceptions.printStackTrace();
                        break;
                    }
                    createdPlans
                            .add(new SocialPlanFactory()
                                    .createInstance(args));
                    break;
                default:
                    Logger.getLogger().error("No such plan type - " + args.get(PLAN_TYPE));
            }
        }
        return createdPlans;
    }

}
