package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DataWriter;
import by.koshko.cyberwikia.dao.cyberpool.ConnectionPool;
import by.koshko.cyberwikia.service.CountryService;
import by.koshko.cyberwikia.service.RandomStringGenerator;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.validation.ValidationPropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

public class DispatcherServlet extends HttpServlet {

    private Logger logger = LogManager.getLogger(getClass());

    @Override
    public void init() {
        try {
            logger.debug(getServletContext().getRealPath("/"));
            ConnectionPool.getInstance().init();
            ValidationPropertiesLoader.loadProperties("validation");
        } catch (DaoException | ServiceException e) {
            logger.error("Cannot initialize application. {}", e.getMessage());
            destroy();
        }
    }

    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {
        String action = (String) req.getAttribute("action");
        if (req.getRequestURI().contains("/profile")) {
            Command c = new PlayerProfileCommand();
            c.execute(req, resp);
        } else if (req.getRequestURI().contains("/players")) {
            Command c1 = new PlayerListCommand();
            c1.execute(req, resp);
        } else if (req.getRequestURI().contains("/teams")) {
            Command c2 = new TeamListCommand();
            c2.execute(req, resp);
        } else if (req.getRequestURI().contains("/registration")) {
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        } else if (req.getRequestURI().contains("/create")) {
            try {
                CountryService cs = ServiceFactory.getCountryService();
                req.setAttribute("countries", cs.getAll());
                req.getRequestDispatcher("WEB-INF/jsp/createTeam.jsp").forward(req, resp);
            } catch (ServiceException e) {
                e.printStackTrace();
            }

        } else if (req.getRequestURI().contains("/signup")) {
            Command c3 = new SignUpCommand();
            c3.execute(req, resp);
        } else if (req.getRequestURI().contains("/team.html")) {
            TeamProfileCommand c = new TeamProfileCommand();
            c.execute(req, resp);
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        logger.info("POST, {}", req.getRequestURI());
        if (req.getRequestURI().contains("/teamcreate")) {
            Part part = null;
            try {
                part = req.getPart("team-logo");
                logger.debug(part == null);
            } catch (IllegalStateException e) {
                logger.error(e.getMessage());
                resp.sendRedirect("index.jsp");
                return;
            }
            logger.debug(part.getContentType());
            logger.debug("COUNTRY ID: {}", req.getParameter("country"));
            String teamName = req.getParameter("team-name");
            int start = part.getContentType().lastIndexOf("/");
            String type = part.getContentType().substring(++start);
            InputStream in = part.getInputStream();
            byte[] bytes = in.readAllBytes();
            logger.debug(bytes.length);
            logger.debug(part.getContentType());
            try {
                String file = DataWriter.write(bytes, getServletContext().getRealPath("/"), "images/upload/", "png");
                logger.debug(file);
            } catch (DaoException e) {
                e.printStackTrace();
            }

        } else {
            SignUpCommand createUser = new SignUpCommand();
            createUser.execute(req, resp);
        }
    }


}
