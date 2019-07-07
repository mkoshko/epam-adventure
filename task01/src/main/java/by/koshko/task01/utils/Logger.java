package by.koshko.task01.utils;

import org.apache.logging.log4j.LogManager;

public class Logger {

    public static org.apache.logging.log4j.Logger getLogger() {
        return LogManager.getLogger("Logger");
    }

}
