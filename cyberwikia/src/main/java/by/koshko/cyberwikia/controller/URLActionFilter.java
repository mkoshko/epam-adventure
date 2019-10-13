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

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
                         final FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String action = getActionFromURI(req);
        AbstractCommand command = CommandProvider.getCommand(action);
        pageToReturn(req, action);
        if (command == null) {
            resp.sendError(404);
        } else {
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

    private void pageToReturn(final HttpServletRequest request,
                              final String action) {
        if (request.getMethod().equals("GET")) {
            if (request.getQueryString() != null) {
                String redirect = action.substring(1) + ".html?"
                                  + request.getQueryString();
                request.setAttribute("from", redirect);
            } else {
                request.setAttribute("from", action.substring(1) + ".html");
            }
        }
    }
}
