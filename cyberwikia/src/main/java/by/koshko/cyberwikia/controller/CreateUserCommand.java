package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.UserService;
import by.koshko.cyberwikia.service.impl.UserServiceImpl;
import by.koshko.cyberwikia.service.validation.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public class CreateUserCommand implements Command {
    Logger logger = LogManager.getLogger(getClass());
    @Override
    public void execute(final HttpServletRequest request, final HttpServletResponse response) {
        Properties properties = new Properties();
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        properties.setProperty("login", login);
        properties.setProperty("email", email);
        properties.setProperty("password", password);
        if (UserValidator.test(properties)) {
            User user = new User();
            user.setLogin(login);
            user.setEmail(email);
            user.setPassword(password);
            try {
                UserService service = new UserServiceImpl();
                service.saveUser(user);
                response.sendRedirect("index.jsp");
            } catch (ServiceException | IOException e) {
                request.setAttribute("error", "Cannot save user.");
                try {
                    response.sendRedirect("index.jsp");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
