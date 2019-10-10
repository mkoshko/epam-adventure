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
        String action = getActionFromURI(req);
        if (req.getMethod().equals("GET")) {
            if (req.getQueryString() != null) {
                String redirect = action.substring(1) + ".html?"
                                  + req.getQueryString();
                req.setAttribute("from", redirect);
            } else {
                req.setAttribute("from", action.substring(1) + ".html");
            }
        }
        AbstractCommand command = commandProvider.getCommand(action);
        if (command == null) {
            logger.debug("Command is null.");
            resp.sendError(404);
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

    private String getActionFromURI(final HttpServletRequest request) {
        logger.debug("Request method: {}", request.getMethod());
        String context = request.getContextPath();
        String requestURI = request.getRequestURI();
        logger.debug("Request URI: {}", requestURI);
        int contextLength = context.length();
        int endRequestIndex = requestURI.lastIndexOf(".html");
        String action;
        if (endRequestIndex != -1) {
            action = requestURI.substring(contextLength, endRequestIndex);
        } else {
            action = "/index";
        }
        logger.debug("Requested action: {}", action);
        return action;
    }
}
