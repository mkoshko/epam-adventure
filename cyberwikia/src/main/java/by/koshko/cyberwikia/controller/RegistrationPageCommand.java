package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationPageCommand extends AbstractCommand {

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        //check for user already logged in.
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return new Forward("index.html", true);
        }
        return new Forward("WEB-INF/jsp/registration.jsp");
    }
}
