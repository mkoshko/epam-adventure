package by.koshko.helloworld;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloWorld {

    /** Logger associated with {@code HelloWorld} class. */
    private static Logger logger = LogManager.getLogger(HelloWorld.class);

    /**
     * Displays given message to a console.
     *
     * @param msg the message string to be displayed.
     */
    public void displayMessage(final String msg) {
        if (msg == null) {
            logger.error("message variable is null");
        } else {
            logger.info("printing message...");
            logger.debug("msg value = " + msg);
            System.out.println(msg);
        }
    }

}
