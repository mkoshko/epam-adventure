package by.koshko.task01.service.validation;

import java.util.List;

import static by.koshko.task01.entity.PlanParameters.*;

public final class PlanParametersValidator {
    private PlanParametersValidator() {
    }

    private static final String NAME_PATTERN = "^[a-zA-z0-9 ]{1,15}";
    private static final String DOUBLE_PATTERN = "^\\d+(\\.\\d+)?";
    private static final String INTEGER_PATTERN = "^\\d*";
    private static final String LONG_PATTERN = "^[0-9]{1,19}";

    private static boolean validateGeneralParams(final List<String> args) {
        return args.get(PLAN_ID).matches(LONG_PATTERN)
                && args.get(PLAN_NAME).matches(NAME_PATTERN)
                && args.get(WITHIN_NETWORK_COST).matches(DOUBLE_PATTERN)
                && args.get(OTHER_NETWORK_COST).matches(DOUBLE_PATTERN)
                && args.get(ABROAD_COST).matches(DOUBLE_PATTERN)
                && args.get(SMS_COST).matches(DOUBLE_PATTERN)
                && args.get(MEGABYTE_COST).matches(DOUBLE_PATTERN);
    }
    private static boolean validateParamsPostpaid(final List<String> args) {
        return validateGeneralParams(args)
                && args.get(SUBSCRIPTION_FEE).matches(DOUBLE_PATTERN);
    }
    private static boolean validateParamsPrepaid(final List<String> args) {
        return validateGeneralParams(args)
                && args.get(FAVOURITE_NUMBER).matches(INTEGER_PATTERN);
    }
    public static boolean validateBasicPlanParams(final List<String> args) {
        return validateParamsPrepaid(args)
                && args.get(MINS_FAVOURITE_NUMBER).matches(INTEGER_PATTERN)
                && args.get(SMS_TO_FAVOURITE_NUMS).matches(INTEGER_PATTERN);
    }
    public static boolean validateInternetPlanParams(final List<String> args) {
        return validateParamsPostpaid(args)
                && args.get(INTERNET_TRAFFIC).matches(INTEGER_PATTERN);
    }
    public static boolean validateSocialPlanParams(final List<String> args) {
        return validateParamsPostpaid(args)
                && args.get(MINUTES_IN_NETWORK).matches(INTEGER_PATTERN)
                && args.get(MINUTES_OTHER_NETWORK).matches(INTEGER_PATTERN)
                && args.get(FREE_SMS).matches(INTEGER_PATTERN);
    }

}
