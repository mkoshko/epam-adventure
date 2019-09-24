package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.cyberpool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    private Logger logger = LogManager.getLogger(getClass());

    @Override
    public void init() {
        try {
            ConnectionPool.access().init();
        } catch (DaoException e) {
            logger.error("Cannot initialize application. {}", e.getMessage());
            destroy();
        }
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        logger.info("GET, {}", req.getRequestURI());
        resp.sendRedirect("registration.jsp");
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        logger.info("POST, {}", req.getRequestURI());
        logger.info("login: {}", req.getParameter("login"));
        CreateUser createUser = new CreateUser();
        createUser.execute(req, resp);
    }
}
