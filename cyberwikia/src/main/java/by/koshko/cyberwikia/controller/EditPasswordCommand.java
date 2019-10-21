package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.ServiceResponse;
import by.koshko.cyberwikia.bean.User;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import by.koshko.cyberwikia.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditPasswordCommand extends AbstractCommand {

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        try (ServiceFactory factory = new ServiceFactory()) {
            UserService userService = factory.getUserService();
            ServiceResponse serviceResponse = userService.updatePassword(
                    user.getId(), oldPassword, newPassword);
            if (!serviceResponse.hasErrors()) {
                return new Forward("index.html");
            } else {
                setErrors(session, serviceResponse);
                return sendBack(request);
            }
        } catch (ServiceException e) {
            getLogger().error(e.getMessage());
            return sendError(500);
        }
    }
}
