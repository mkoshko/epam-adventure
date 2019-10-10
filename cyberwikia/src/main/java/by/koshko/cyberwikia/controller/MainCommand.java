package by.koshko.cyberwikia.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainCommand extends AbstractCommand {
    @Override
    public Forward execute(final HttpServletRequest request,
                        final HttpServletResponse response) {
        return new Forward("WEB-INF/jsp/index.jsp");
    }
}
