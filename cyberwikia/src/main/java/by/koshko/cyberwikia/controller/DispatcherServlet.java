package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Stream;

public class DispatcherServlet extends HttpServlet {

    private Logger logger = LogManager.getLogger(getClass());

    @Override
    public void init() {
        try {
            Properties conf = new Properties();
            conf.put("bundle", "validation");
            conf.put("path", getServletContext().getRealPath("/"));
            ServiceInitializer.init(conf);
        } catch (ServiceException e) {
            logger.error("Cannot initialize application. {}", e.getMessage());
            destroy();
        }
    }

    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp) {
        saveLastAction(req, resp);
        AbstractCommand command = (AbstractCommand) req.getAttribute("command");
        AbstractCommand.Forward forward = command.execute(req, resp);
        try {
            if (!checkError(forward, resp)) {
                if (forward.isRedirect()) {
                    resp.sendRedirect(forward.getUrl());
                } else {
                    req.getRequestDispatcher(forward.getUrl()).forward(req, resp);
                }
            }
        } catch (ServletException | IOException e) {
            logger.error("Cannot forward user. {}", e.getMessage());
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) {
        AbstractCommand command = (AbstractCommand) req.getAttribute("command");
        AbstractCommand.Forward forward = command.execute(req, resp);
        try {
            if (!checkError(forward, resp)) {
                logger.debug("Redirecting to {}", forward.getUrl());
                resp.sendRedirect(forward.getUrl());
            }
        } catch (IOException e) {
            logger.error("Cannot redirect user. {}", e.getMessage());
        }

    }

    private boolean checkError(final AbstractCommand.Forward forward,
                               final HttpServletResponse response) {
        if (forward.isError()) {
            logger.debug("Forward is error.");
            try {
                int error = (Integer) forward.getAttributes().get("error");
                response.sendError(error);
                return true;
            } catch (IOException e) {
                logger.error("Cannot redirect user.");
                return true;
            }
        } else {
            return false;
        }
    }

    private void saveLastAction(final HttpServletRequest request,
                                final HttpServletResponse response) {
        String currentAction = (String) request.getAttribute("currentAction");
        currentAction = currentAction.substring(1) + ".html";
        currentAction = checkForParameters(currentAction, request);
        Optional<Cookie> cookieOpt = Stream.of(request.getCookies())
                .filter(c -> c.getName().equals("page"))
                .findFirst();
        cookieOpt.ifPresent(c -> request.setAttribute("page", c.getValue()));
        logger.debug("Page to return: {}", currentAction);
        Cookie cookie = new Cookie("page", currentAction);
        response.addCookie(cookie);
    }

    private String checkForParameters(final String currentAction,
                                    final HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        if (!params.isEmpty()) {
            Set<String> keys = params.keySet();
            StringBuilder newCurrentAction = new StringBuilder(currentAction);
            newCurrentAction.append("?");
            keys.forEach(key -> {
                newCurrentAction.append(key + "=" + params.get(key)[0]);
            });
            return newCurrentAction.toString();
        }
        return currentAction;
    }
}
