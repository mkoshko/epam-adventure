package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpCommand extends AbstractCommand {

    private Logger logger = LogManager.getLogger(SignUpCommand.class);

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try (ServiceFactory factory = new ServiceFactory()) {
            User user = new User();
            user.setLogin(login);
            user.setEmail(email);
            user.setPassword(password);
            UserService userService = factory.getUserService();
            int statusCode = userService.sighUp(user);
            if (request.getSession(false) == null) {
                if (statusCode == 0) {
                    return new Forward("index.html");
                } else {
                    return new Forward("registration.html");
                }
            }
            switch (statusCode) {
                case 0:
                    return new Forward("index.html");
                case 1:
                    request.getSession(false).setAttribute("registrationError", "duplicate.login");
                    return new Forward("registration.html");
                case 2:
                    request.getSession(false).setAttribute("registrationError", "duplicate.email");
                    return new Forward("registration.html");
                case 3:
                    request.getSession(false).setAttribute("registrationError", "duplicate.loginAndEmail");
                    return new Forward("registration.html");
                case -1:
                    return new Forward("registration.html");
                default:
                    return sendError(404);
            }
        } catch (ServiceException e) {
            return sendError(500);
        }
    }
}
