package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.service.PlayerService;
import by.koshko.cyberwikia.service.impl.PlayerServiceImpl;
import by.koshko.cyberwikia.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PlayerListCommand implements Command {

    private Logger logger = LogManager.getLogger(getClass());

    @Override
    public void execute(final HttpServletRequest request,
                        final HttpServletResponse response) {
        try {
            PlayerService playerService = new PlayerServiceImpl();
            request.setAttribute("players", playerService.findAll());
            request.getRequestDispatcher("WEB-INF/jsp/players.jsp").forward(request, response);
        } catch (ServiceException e) {

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
