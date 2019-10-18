package by.koshko.cyberwikia.controller.user;

import by.koshko.cyberwikia.controller.UserCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignOutCommand extends UserCommand {
    private Logger logger = LogManager.getLogger(SignOutCommand.class);
    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
            session.invalidate();
        }
        return sendBack(request);
    }
}
