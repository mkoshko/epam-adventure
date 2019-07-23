package by.koshko.task01.service.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PlanParameterSeparator {

    /** Regex for splitting params. */
    private static final String SPLITTER_REGEX = ",";
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
     */
    public static List<String> separate(final String params) {
        if (params == null) {
            throw new NullPointerException();
        }
        List<String> splitted = Arrays.asList(params.split(SPLITTER_REGEX));
        List<String> trimmed = new ArrayList<>();
        splitted.forEach((str) -> trimmed.add(str.trim()));
        return trimmed;
    }

}
