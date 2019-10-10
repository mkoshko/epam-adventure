package by.koshko.cyberwikia.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignOutCommand extends AbstractCommand {
    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
            session.invalidate();
        }
        return tryToReturnLastPage(request);
    }
}
