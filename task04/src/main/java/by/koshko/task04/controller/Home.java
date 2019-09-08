package by.koshko.task04.controller;

import by.koshko.task04.entity.Banks;
import by.koshko.task04.service.BankBuilderException;
import by.koshko.task04.service.BanksBuilderFactory;
import org.apache.logging.log4j.LogManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class Home extends HttpServlet {

    private BanksBuilderFactory factory = new BanksBuilderFactory();

    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String parser = req.getParameter("parser");
        String path = req.getParameter("path");
        var builder = factory.createBanksBuilder(parser, getServletContext()
                .getResource("data/banks.xsd").getPath());
        try {
            if (!path.isEmpty()) {
                Banks b = builder.buildBanks(path);
                req.setAttribute("banks", b.getBanks());
            }
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (BankBuilderException e) {
            LogManager.getLogger(getClass()).error(e.getMessage());
        }
    }
}
