package by.koshko.cyberwikia.controller.user;

import by.koshko.cyberwikia.bean.*;
import by.koshko.cyberwikia.controller.UserCommand;
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
            Part part = request.getPart("profilePhoto");
            player.setRawData(fillRawData(part));
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
                LOGGER.debug("Service response have some errors.");
                setErrors(session, serviceResponse);
                return new Forward("createplayerform.html");
            } else {
                return new Forward("mypages.html");
            }
        } catch (DateTimeParseException | NumberFormatException e) {
            ServiceResponse serviceResponse = new ServiceResponse();
            serviceResponse.addErrorMessage(EntityError.REQUIRED_NOT_NULL);
            setErrors(session, serviceResponse);
            return new Forward("createplayerform.html");
        } catch (ServiceException | IOException | ServletException e1) {
            LOGGER.error(e1.getMessage());
            return sendError(500);
        }
    }
}
