package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInCommand extends AbstractCommand {
    private Logger logger = LogManager.getLogger(SignInCommand.class);

    @Override
    public Forward execute(final HttpServletRequest request,
                        final HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try (ServiceFactory factory = new ServiceFactory()) {
            UserService userService = factory.getUserService();
            User user = userService.signIn(login, password);
            if (user != null) {
                logger.debug("User {} logged in.", user.getLogin());
                request.getSession(true).setAttribute("user", user);
            }
            return new Forward(request.getParameter("from"));
        } catch (ServiceException e) {
            Forward forward = new Forward();
            forward.setError(true);
            forward.getAttributes().put("error", 500);
            return forward;
        }
    }
}
