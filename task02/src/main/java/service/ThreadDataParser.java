package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Class contains method for parsing string with parameters separated by space,
 * to string array where each string contains single parameter.
 */
public final class ThreadDataParser {
    /**
     * Logger.
     */
    private static Logger log
            = LogManager.getLogger(ThreadDataParser.class);
    /**
     * Regex expression for splitting arguments.
     */
    private static final String SPLIT_REGEX = "\\s";
    private ThreadDataParser() {
    }

    /**
     * Splits parameters from string to string array, where each string
     * is separate parameter.
     * @param lines strings to be parsed.
     * @return list of strings array, where each string in array is
     * single parameter.
     */
    public static List<String[]> parse(final List<String> lines) {
        if (lines == null) {
            log.info("Attempt to parse null list of strings.");
            return new ArrayList<>();
        }
        List<String[]> result = new ArrayList<>();
        for (String s : lines) {
            result.add(s.split(SPLIT_REGEX));
        }
        return result;
    }
}
