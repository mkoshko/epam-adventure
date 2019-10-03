package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.service.PlayerService;
import by.koshko.cyberwikia.service.impl.PlayerServiceImpl;
import by.koshko.cyberwikia.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PlayerProfileCommand implements Command {
    private Logger logger = LogManager.getLogger(getClass());
    @Override
    public void execute(final HttpServletRequest request, final HttpServletResponse response) {
        try {
            int b = Integer.parseInt(request.getParameter("id"));
            logger.debug("profile id: {}", b);
            PlayerService ps = new PlayerServiceImpl();
            Player player = ps.loadProfile(b);
            request.setAttribute("player", player);
            request.getRequestDispatcher("WEB-INF/jsp/profile.jsp").forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
