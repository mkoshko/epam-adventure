package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.dao.cyberpool.ConnectionPool;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public class DispatcherServlet extends HttpServlet {

    private Logger logger = LogManager.getLogger(DispatcherServlet.class);

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
            resp.sendRedirect(forward.getUrl());
        } catch (IOException e) {
            logger.error("Cannot redirect user. {}", e.getMessage());
        }

    }

    private boolean checkError(final AbstractCommand.Forward forward,
                               final HttpServletResponse response) {
        if (forward.isError()) {
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

    @Override
    public void destroy() {
        logger.debug("Closing connection pool.");
        ConnectionPool.getInstance().close();
    }
}
