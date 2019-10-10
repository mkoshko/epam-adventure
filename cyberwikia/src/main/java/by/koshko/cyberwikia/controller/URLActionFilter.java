package by.koshko.cyberwikia.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class URLActionFilter implements Filter {
    private static Logger logger = LogManager.getLogger(URLActionFilter.class);
    private CommandProvider commandProvider = new CommandProvider();
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
                         final FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        logger.debug("Request method: {}", req.getMethod());
        String context = req.getContextPath();
        String requestURI = req.getRequestURI();
        logger.debug("Request URI: {}", requestURI);
        int contextLength = context.length();
        int endRequestIndex = requestURI.lastIndexOf(".html");
        String action;
        if (endRequestIndex != -1) {
            action = requestURI.substring(contextLength, endRequestIndex);
        } else {
            action = "/";
        }
        logger.debug("Requested action: {}", action);
        if (req.getMethod().equals("GET")) {
            req.setAttribute("currentAction", action);
        }
        AbstractCommand command = commandProvider.getCommand(action);
        if (command == null) {
            resp.sendRedirect("404.html");
        } else {
            logger.debug("Got a command.");
            request.setAttribute("command", command);
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
