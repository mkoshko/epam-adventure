package by.koshko.task01.service.validation;

import by.koshko.task01.entity.Plan;

import java.util.List;

import static by.koshko.task01.entity.PlanParameters.FREE_SMS;
import static by.koshko.task01.entity.PlanParameters.MINUTES_OTHER_NETWORK;
import static by.koshko.task01.entity.PlanParameters.MINUTES_IN_NETWORK;
import static by.koshko.task01.entity.PlanParameters.FAVOURITE_NUMBER;
import static by.koshko.task01.entity.PlanParameters.SMS_TO_FAVOURITE_NUMS;
import static by.koshko.task01.entity.PlanParameters.MEGABYTE_COST;
import static by.koshko.task01.entity.PlanParameters.PLAN_ID;
import static by.koshko.task01.entity.PlanParameters.PLAN_NAME;
import static by.koshko.task01.entity.PlanParameters.WITHIN_NETWORK_COST;
import static by.koshko.task01.entity.PlanParameters.OTHER_NETWORK_COST;
import static by.koshko.task01.entity.PlanParameters.ABROAD_COST;
import static by.koshko.task01.entity.PlanParameters.SMS_COST;
import static by.koshko.task01.entity.PlanParameters.SUBSCRIPTION_FEE;
import static by.koshko.task01.entity.PlanParameters.MINS_FAVOURITE_NUMBER;
import static by.koshko.task01.entity.PlanParameters.INTERNET_TRAFFIC;

public final class PlanParameterValidator {
    private PlanParameterValidator() {
    }

    /**
     * Regex pattern for names.
     */
    private static final String NAME_PATTERN = "^[a-zA-z0-9 ]{1,15}";
    /**
     * Regex pattern for double values.
     */
    private static final String DOUBLE_PATTERN = "^\\d+(\\.\\d+)?";
    /**
     * Regex pattern for integer values.
     */
    private static final String INTEGER_PATTERN = "^\\d{1,10}";
    /**
     * Regex pattern for long values.
     */
    private static final String LONG_PATTERN = "^\\d{1,19}";

    private static boolean validateCommonParams(final List<String> args) {
        return args.get(PLAN_ID).matches(LONG_PATTERN)
               && args.get(PLAN_NAME).matches(NAME_PATTERN)
               && args.get(WITHIN_NETWORK_COST).matches(DOUBLE_PATTERN)
               && args.get(OTHER_NETWORK_COST).matches(DOUBLE_PATTERN)
               && args.get(ABROAD_COST).matches(DOUBLE_PATTERN)
               && args.get(SMS_COST).matches(DOUBLE_PATTERN)
               && args.get(MEGABYTE_COST).matches(DOUBLE_PATTERN);
    }

    private static boolean validateParamsPostpaid(final List<String> args) {
        return validateCommonParams(args)
                && args.get(SUBSCRIPTION_FEE).matches(DOUBLE_PATTERN);
    }

    private static boolean validateParamsPrepaid(final List<String> args) {
        return validateCommonParams(args)
                && args.get(FAVOURITE_NUMBER).matches(INTEGER_PATTERN);
    }

    /**
     * Validate parameters required for {@code BasicPlan}.
     * @param args {@code List} contains parameters required
     *             for object creation.
     * @return {@code true} if parameters is valid, {@code false} otherwise.
     */
    public static boolean validateBasicPlanParams(final List<String> args) {
        return CheckParamsNumber.check(Plan.PlanType.BASIC, args)
                && validateParamsPrepaid(args)
                && args.get(MINS_FAVOURITE_NUMBER).matches(INTEGER_PATTERN)
                && args.get(SMS_TO_FAVOURITE_NUMS).matches(INTEGER_PATTERN);
    }

    /**
     * Validate parameters required for {@code InternetPlan}.
     * @param args {@code List} contains parameters required
     *             for object creation.
     * @return {@code true} if parameters is valid, {@code false} otherwise.
     */
    public static boolean validateInternetPlanParams(final List<String> args) {
        return CheckParamsNumber.check(Plan.PlanType.INTERNET, args)
                && validateParamsPostpaid(args)
                && args.get(INTERNET_TRAFFIC).matches(INTEGER_PATTERN);
    }

    /**
     * Validate parameters required for {@code SocialPlan}.
     * @param args {@code List} contains parameters required
     *             for object creation.
     * @return {@code true} if parameters is valid, {@code false} otherwise.
     */
    public static boolean validateSocialPlanParams(final List<String> args) {
        return CheckParamsNumber.check(Plan.PlanType.SOCIAL, args)
                && validateParamsPostpaid(args)
                && args.get(MINUTES_IN_NETWORK).matches(INTEGER_PATTERN)
                && args.get(MINUTES_OTHER_NETWORK).matches(INTEGER_PATTERN)
                && args.get(FREE_SMS).matches(INTEGER_PATTERN);
    }

}
