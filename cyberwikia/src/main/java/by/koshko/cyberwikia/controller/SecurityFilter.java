package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Role;
import by.koshko.cyberwikia.bean.User;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class SecurityFilter implements Filter {
    private Logger logger = LogManager.getLogger(SecurityFilter.class);
    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain) throws
            IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        AbstractCommand command = (AbstractCommand) req.getAttribute("command");
        HttpSession session = req.getSession(false);
        Set<Role> commandRoles = command.getRoles();
        if (commandRoles.isEmpty()) {
            logger.debug("Roles is empty.");
            chain.doFilter(req, resp);
        } else {
            User user = (User) session.getAttribute("user");
            boolean canExecute = user != null && commandRoles.contains(user.getRole());
            if (canExecute) {
                logger.debug("Can execute. Continue.");
                chain.doFilter(req, response);
            } else {
                resp.sendError(404);
            }
        }
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
