package by.koshko.cyberwikia.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LanguageCommand implements Command {
    private Logger logger = LogManager.getLogger(LanguageCommand.class);
    private static Map<String, String> lang = new HashMap<>();

    static {
        lang.put("ru", "ru_RU");
        lang.put("en", "en_US");
    }

    @Override
    public void execute(final HttpServletRequest request,
                        final HttpServletResponse response) {
        String langParam = request.getParameter("id");
        String locale = lang.get(langParam);
        String currentPage = (String) request.getAttribute("action");
        currentPage = currentPage.substring(1) + ".html";
        try {
            if (locale == null) {
                response.sendRedirect("index.html");
            } else {
                Cookie cookie = new Cookie("locale", locale);
                cookie.setMaxAge(Integer.MAX_VALUE);
                response.addCookie(cookie);
                response.sendRedirect("index.html");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
