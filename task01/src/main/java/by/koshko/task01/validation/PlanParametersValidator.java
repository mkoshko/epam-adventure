package by.koshko.task01.validation;

import by.koshko.task01.exception.IllegalPlanParametersExceptions;

import java.util.List;

public final class PlanParametersValidator {
    private PlanParametersValidator() {
    }

    private static final int PLAN_NAME           = 1;
    private static final int MINUTE_COST         = 2;
    private static final int MEGABYTE_COST       = 3;
    private static final int SMS_COST            = 4;
    private static final int PRICE               = 5;
    private static final int INTERNET_PACK       = 6;
    private static final int OTHER_NETWORK_COST  = 6;
    private static final int WITHIN_NETWORK_COST = 7;
    private static final int SMS_PACK            = 8;
    private static final String NAME_PATTERN = "^[a-zA-z0-9 ]{1,15}";
    private static final String DOUBLE_PATTERN = "^\\d+(\\.\\d+)?";
    private static final String INTEGER_PATTERN = "^\\d*";

    private static void validateGeneralParams(final List<String> args) throws IllegalPlanParametersExceptions {
        if (!args.get(PLAN_NAME).matches(NAME_PATTERN)) {
            throw new IllegalPlanParametersExceptions("Parameter 'planName' is invalid.");
        }
        if (!args.get(MINUTE_COST).matches(DOUBLE_PATTERN)) {
            throw new IllegalPlanParametersExceptions("Parameter 'costMinute' is invalid.");
        }
        if (!args.get(MEGABYTE_COST).matches(DOUBLE_PATTERN)) {
            throw new IllegalPlanParametersExceptions("Parameter 'costMegabyte' is invalid.");
        }
        if (!args.get(SMS_COST).matches(DOUBLE_PATTERN)) {
            throw new IllegalPlanParametersExceptions("Parameter 'costSms' is invalid.");
        }
    }
    public static void validateParamsForCommonPlan(final List<String> args) throws IllegalPlanParametersExceptions {
        validateGeneralParams(args);
    }
    public static void validateGeneralParamsWithPrice(final List<String> args) throws IllegalPlanParametersExceptions {
        validateGeneralParams(args);
        if (!args.get(PRICE).matches(DOUBLE_PATTERN)) {
            throw new IllegalPlanParametersExceptions("Parameter 'price' is invalid.");
        }
    }
    public static void validateParamsForInternetPlan(final List<String> args) throws IllegalPlanParametersExceptions {
        validateGeneralParamsWithPrice(args);
        if (!args.get(INTERNET_PACK).matches(INTEGER_PATTERN)) {
            throw new IllegalPlanParametersExceptions("Parameter 'internetPack' is invalid.");
        }
    }
    public static void validateParamsForSocialPlan(final List<String> args) throws IllegalPlanParametersExceptions {
        validateGeneralParamsWithPrice(args);
        if (!args.get(OTHER_NETWORK_COST).matches(DOUBLE_PATTERN)) {
            throw new IllegalPlanParametersExceptions("Parameter 'otherNetworks' is invalid.");
        }
        if (!args.get(WITHIN_NETWORK_COST).matches(DOUBLE_PATTERN)) {
            throw new IllegalPlanParametersExceptions("Parameter 'withinNetwork' is invalid.");
        }
        if (!args.get(SMS_PACK).matches(INTEGER_PATTERN)) {
            throw new IllegalPlanParametersExceptions("Parameter 'includedPack' is invalid.");
        }
    }

}
