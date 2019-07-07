package by.koshko.task01.factory;

import by.koshko.task01.entity.CommonPlan;
import by.koshko.task01.entity.InternetPlan;
import by.koshko.task01.entity.SocialPlan;
import by.koshko.task01.exception.IllegalPlanParametersExceptions;
import by.koshko.task01.utils.Logger;
import by.koshko.task01.utils.PlanParameterSeparator;
import java.util.ArrayList;
import java.util.List;

public final class PlanFactory {
    private static final String ARGS_ERROR_MESSAGE = "Wrong numbers of parameters";
    private PlanFactory() {
    }

    public static List<CommonPlan> createPlans(final List<String> params) {
        List<CommonPlan> createdPlans = new ArrayList<>();
        for (int i = 0; i < params.size(); i++) {
            List<String> argsForOneObject;
            try {
                argsForOneObject = PlanParameterSeparator
                        .separate(params.get(i));
            } catch (IllegalPlanParametersExceptions e) {
                e.printStackTrace();
                continue;
            }
            switch (argsForOneObject.get(0).toUpperCase()) {
                case "COMMON":
                    if (argsForOneObject.size() != CommonPlan.REQUIRED_PARAMETERS + 1) {
                        Logger.getLogger().error(ARGS_ERROR_MESSAGE + " for common plan");
                        break;
                    }
                    createdPlans
                            .add(new CommonPlanFactory()
                            .createInstance(argsForOneObject));
                    break;
                case "INTERNET":
                    if (argsForOneObject.size() != InternetPlan.REQUIRED_PARAMETERS + 1) {
                        Logger.getLogger().error(ARGS_ERROR_MESSAGE + " for internet plan");
                        break;
                    }
                    createdPlans
                            .add(new InternetPlanFactory()
                            .createInstance(argsForOneObject));
                    break;
                case "SOCIAL":
                    if (argsForOneObject.size() != SocialPlan.REQUIRED_PARAMETERS + 1) {
                        Logger.getLogger().error(ARGS_ERROR_MESSAGE + " for social plan");
                        break;
                    }
                    createdPlans
                            .add(new SocialPlanFactory()
                            .createInstance(argsForOneObject));
                    break;
                default:
                    Logger.getLogger().error("No such plan type - " + argsForOneObject.get(0));
            }
        }
        return createdPlans;
    }

}
