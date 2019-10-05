package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.FileManager;
import by.koshko.cyberwikia.service.CountryService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.ServiceInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DispatcherServlet extends HttpServlet {

    private Logger logger = LogManager.getLogger(getClass());
    private Controller controller = new Controller();
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
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) {

    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)  {

    }


}
