package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpCommand implements Command {
    private Logger logger = LogManager.getLogger(SignUpCommand.class);

    @Override
    public void execute(final HttpServletRequest request, final HttpServletResponse response) {
        logger.debug("execution");
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        logger.debug("Creation user.");
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        try {
            logger.debug("saving user.");
            ServiceFactory.getUserService().create(user);
            response.sendRedirect("index.jsp");
            logger.debug("saved.");
        } catch (ServiceException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
