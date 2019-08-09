package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public final class ThreadPropertyParser {
    private static Logger log
            = LogManager.getLogger(ThreadPropertyParser.class);
    private static final String SPLIT_REGEX = "\\s";
    private ThreadPropertyParser() {
    }
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
