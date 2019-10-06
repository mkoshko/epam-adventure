package by.koshko.cyberwikia.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainCommand implements Command {
    @Override
    public void execute(final HttpServletRequest request,
                        final HttpServletResponse response) {
        try {
            request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            throw new RuntimeException();
        }
    }
}
