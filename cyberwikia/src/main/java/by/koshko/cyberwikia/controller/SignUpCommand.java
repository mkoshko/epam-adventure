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
            boolean success = userService.sighUp(user);
            if (success) {
                logger.debug("User {} registered successfully.", user.getLogin());
                return new Forward("index.html");
            } else {
                logger.debug("User wasn't registered.");
                return new Forward("registration.html");
            }

        } catch (ServiceException e) {
            return sendError(500);
        }
    }
}
