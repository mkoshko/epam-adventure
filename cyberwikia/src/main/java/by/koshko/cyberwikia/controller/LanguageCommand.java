package by.koshko.cyberwikia.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class LanguageCommand extends AbstractCommand {
    private Logger logger = LogManager.getLogger(LanguageCommand.class);
    private static Map<String, String> lang = new HashMap<>();

    static {
        lang.put("ru", "ru_RU");
        lang.put("en", "en_US");
    }

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        String langParam = request.getParameter("id");
        String locale = lang.get(langParam);
        if (locale == null) {
            return new Forward("index.html", true);
        } else {
            Cookie cookie = new Cookie("locale", locale);
            logger.debug("Add cookie.");
            response.addCookie(cookie);
            String page = (String) request.getAttribute("page");
            logger.debug("Page to return: {}", page);
            return new Forward(page, true);
        }
    }
}
