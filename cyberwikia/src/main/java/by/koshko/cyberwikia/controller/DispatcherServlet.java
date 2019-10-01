package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.cyberpool.ConnectionPool;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ValidationPropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DispatcherServlet extends HttpServlet {

    private Logger logger = LogManager.getLogger(getClass());

    @Override
    public void init() {
        try {
            ConnectionPool.getInstance().init();
            ValidationPropertiesLoader.loadProperties("validation");
        } catch (DaoException e) {
            logger.error("Cannot initialize application. {}", e.getMessage());
            destroy();
        } catch (ServiceException e) {
        }
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().contains("/profile")) {
            Command c = new PlayerProfileCommand();
            c.execute(req, resp);
        } else if (req.getRequestURI().contains("/players")) {
            Command c1 = new PlayerListCommand();
            c1.execute(req, resp);
        } else if (req.getRequestURI().contains("/teams")) {
            Command c2 = new TeamListCommand();
            c2.execute(req, resp);
        } else if (req.getRequestURI().contains("/registration")) {
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        } else if (req.getRequestURI().contains("/create")) {
            req.getRequestDispatcher("createTeam.jsp").forward(req, resp);
        } else if (req.getRequestURI().contains("/signup")) {
            Command c3 = new SignUpCommand();
            c3.execute(req, resp);
        } else if (req.getRequestURI().contains("/team.html")) {
            TeamPageCommand c = new TeamPageCommand();
            c.execute(req, resp);
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        logger.info("POST, {}", req.getRequestURI());
        if (req.getRequestURI().contains("/teamcreate")) {
            Part part = req.getPart("team-logo");
            logger.debug(part.getContentType());
            String teamName = req.getParameter("team-name");
            int start = part.getContentType().lastIndexOf("/");
            String type = part.getContentType().substring(++start);
            InputStream in = part.getInputStream();
            byte[] bytes = in.readAllBytes();
            in.reset();
            BufferedImage bi = ImageIO.read(in);
            logger.debug(bi.getHeight());
            logger.debug(bi.getWidth());
            File file = new File(getServletContext().getResource("/").getPath() + "images/teams/" + teamName + "." + type);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.close();
        } else {
            SignUpCommand createUser = new SignUpCommand();
            createUser.execute(req, resp);
        }
    }


}
