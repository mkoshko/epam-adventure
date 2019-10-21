package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditPasswordPageCommand extends AbstractCommand {

    private Logger logger = LogManager.getLogger();

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user != null) {
            setScript(request, "js/edit-password-validation.js");
            return new Forward("WEB-INF/jsp/changepassword.jsp");
        } else {
            return sendError(404);
        }
    }
}
