package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.service.PlayerService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInCommand implements Command {
    private Logger logger = LogManager.getLogger(SignInCommand.class);

    @Override
    public void execute(final HttpServletRequest request,
                        final HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try (ServiceFactory factory = new ServiceFactory();) {
            UserService userService = factory.getUserService();
            User user = userService.signIn(login, password);
            if (user != null) {
                logger.debug("User {} logged in.", user.getLogin());
                request.getSession(true).setAttribute("user", user);
                if (request.getCookies() == null) {
                    response.sendRedirect(response.encodeRedirectURL("index.html"));
                    return;
                }
                response.sendRedirect("index.html");
            } else {
                response.sendRedirect("index.html");
            }
        } catch (ServiceException e) {

        } catch (IOException e1) {

        }
    }
}
