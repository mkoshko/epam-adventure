package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.*;
import by.koshko.cyberwikia.service.PlayerService;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class PlayerEditCommand extends UserCommand {

    private Logger logger = LogManager.getLogger(PlayerEditCommand.class);

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        HttpSession session = request.getSession();
        try (ServiceFactory factory = new ServiceFactory()) {
            PlayerService playerService = factory.getPlayerService();
            User user = (User) session.getAttribute("user");
            Player player = new Player();
            player.setNickname(request.getParameter("nickname"));
            player.setOverview(request.getParameter("overview"));
            player.setFirstName(request.getParameter("firstname"));
            player.setLastName(request.getParameter("lastname"));
            Part part = request.getPart("profilePhoto");
            player.setRawData(fillRawData(part));
            player.setCountry(new Country(Integer
                    .parseInt(request.getParameter("country"))));
            player.setBirth(LocalDate.parse(Objects.requireNonNullElse(request.getParameter("birth"), "")));
            ServiceResponse serviceResponse
                    = playerService.editPlayer(user.getId(), player);
            if (serviceResponse.hasErrors()) {
                session.setAttribute("errors", serviceResponse.errorList());
                return new Forward("editplayer.html");
            } else {
                return new Forward("player.html?id=" + user.getId());
            }
        } catch (ServiceException | IOException | ServletException e) {
            logger.error(e.getMessage());
            return sendError(500);
        } catch (DateTimeParseException | NumberFormatException e) {
            session.setAttribute("errors", "editplayer.error.fillrequired");
            return new Forward("editplayer.html");
        }
    }
}
