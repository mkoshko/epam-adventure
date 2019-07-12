package by.koshko.task01.service;

import by.koshko.task01.entity.BasicPlan;
import by.koshko.task01.entity.InternetPlan;
import by.koshko.task01.entity.Plan;
import by.koshko.task01.entity.SocialPlan;
import java.util.List;

public final class CheckParamsNumber {
    private CheckParamsNumber() {
    }

    /**
     * Checks if a number of arguments is a valid for specified type of a plan.
     *
     * @param type Type of a plan.
     * @param args Arguments to be checked.
     * @return {@code true} if amount of arguments is valid for specified
     * plan type, {@code false} otherwise.
     */
    public static boolean check(final Plan.PlanType type, final List<String> args) {
        switch (type) {
            case BASIC:
                return args.size() == BasicPlan.REQUIRED_ARGUMENTS + 1;
            case SOCIAL:
                return args.size() == SocialPlan.REQUIRED_ARGUMENTS + 1;
            case INTERNET:
                return args.size() == InternetPlan.REQUIRED_ARGUMENTS + 1;
            default:
                return false;
        }
    }
}
