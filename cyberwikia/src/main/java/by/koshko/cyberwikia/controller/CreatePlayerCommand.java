package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Country;
import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.ServiceResponse;
import by.koshko.cyberwikia.bean.User;
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

public class CreatePlayerCommand extends UserCommand {

    private static final Logger LOGGER
            = LogManager.getLogger(CreatePlayerCommand.class);

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        try (ServiceFactory factory = new ServiceFactory()) {
            User user = (User) session.getAttribute("user");
            PlayerService playerService = factory.getPlayerService();
            Player player = new Player();
            player.setId(user.getId());
//            Part part = request.getPart("profilePhoto");
//            player.setRawData(fillRawData(part));
            player.setNickname(request.getParameter("nickname"));
            player.setFirstName(request.getParameter("firstname"));
            player.setLastName(request.getParameter("lastname"));
            player.setOverview(request.getParameter("overview"));
            player.setCountry(new Country(Integer
                    .parseInt(request.getParameter("country"))));
            player.setBirth(LocalDate.parse(request.getParameter("birth")));
            ServiceResponse serviceResponse
                    = playerService.createPlayer(user.getId(), player);
            if (serviceResponse.hasErrors()) {
                LOGGER.debug("Response has some errors.");
                setErrors(session, serviceResponse);
                return new Forward("createplayerform.html");
            } else {
                return new Forward("mypages.html");
            }
        } catch (NumberFormatException e) {
            session.setAttribute("errors", "createplayer.error.notsaved");
            return new Forward("createplayerform.html");
        }catch (DateTimeParseException e1) {
            session.setAttribute("errors", "editplayer.error.fillrequired");
            return new Forward("createplayerform.html");
        } catch (ServiceException e2) {
            LOGGER.error(e2.getMessage());
            return sendError(500);
        }
    }
}
