package by.koshko.task01.utils;

import by.koshko.task01.exception.IllegalPlanParametersExceptions;
import java.util.Arrays;
import java.util.List;

public final class PlanParameterSeparator {
    /** Default message for {@code IllegalPlanParametersExceptions}. */
    private static final String EXCEPTION_MESSAGE = "String with params is empty";
    private PlanParameterSeparator() {
    }
    /**
     *
     * @param params string with single line parameters.
     * @return {@code List<String>} with divided parameters. For example:
     *         param1, param2, param3 ... paramN.
     *         Divides to list:
     *         param1
     *         param2
     *         param3
     *         ...
     *         paramN.
     * @throws IllegalPlanParametersExceptions if a string is empty.
     */
    public static List<String> separate(final String params) throws IllegalPlanParametersExceptions {
        if (params == null) {
            throw new NullPointerException();
        }
        if (params.length() == 0) {
            Logger.getLogger().error(EXCEPTION_MESSAGE);
            throw new IllegalPlanParametersExceptions(EXCEPTION_MESSAGE);
        }
        List<String> list = Arrays.asList(params.split("\\,"));
        return list;
    }

}
