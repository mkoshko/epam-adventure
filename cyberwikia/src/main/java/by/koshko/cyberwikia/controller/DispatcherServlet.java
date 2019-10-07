package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;
import java.util.stream.Stream;

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
        controller.executeCommand(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) {
        controller.executeCommand(req, resp);
    }


}
