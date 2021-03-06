package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.ServiceResponse;
import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
            ServiceResponse serviceResponse = userService.sighUp(user);
            HttpSession session = request.getSession(true);
            if (!serviceResponse.hasErrors()) {
                return new Forward("index.html");
            } else {
                setErrors(session, serviceResponse);
                return new Forward("registration.html");
            }
        } catch (ServiceException e) {
            return sendError(500);
        }
    }
}
