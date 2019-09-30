package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.cyberpool.ConnectionPool;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ValidationPropertiesLoader;
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
            ConnectionPool.getInstance().init();
            ValidationPropertiesLoader.loadProperties("validation");
        } catch (DaoException e) {
            logger.error("Cannot initialize application. {}", e.getMessage());
            destroy();
        } catch (ServiceException e) {
        }
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        logger.info("GET, {}", req.getRequestURI());
        logger.info("GET, {}", req.getContextPath());
        logger.info(req.getRequestURI().split(req.getContextPath())[1]);
        if (req.getRequestURI().contains("/profile")) {
            Command c = new PlayerProfileCommand();
            c.execute(req, resp);
        } else if (req.getRequestURI().contains("/players")) {
            Command c1 = new PlayerListCommand();
            c1.execute(req, resp);
        } else if (req.getRequestURI().contains("/team")) {
            Command c2 = new TeamListCommand();
            c2.execute(req, resp);
        } else if (req.getRequestURI().contains("/registration")) {
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        } else if (req.getRequestURI().contains("/signup")) {
            Command c3 = new SignUpCommand();
            c3.execute(req, resp);
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        logger.info("POST, {}", req.getRequestURI());
        logger.info("login: {}", req.getParameter("login"));
        SignUpCommand createUser = new SignUpCommand();
        createUser.execute(req, resp);
    }


}
