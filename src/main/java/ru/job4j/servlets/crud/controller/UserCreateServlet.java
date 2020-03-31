package ru.job4j.servlets.crud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.servlets.crud.User;
import ru.job4j.servlets.crud.model.Validate;
import ru.job4j.servlets.crud.model.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserCreateServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UserCreateServlet.class);

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = (String) req.getAttribute("action");
        if (action.equals("create")) {
            req.getRequestDispatcher("/WEB-INF/views/AddUser.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user;
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        user = new User(Integer.parseInt(id), name);
        try {
            logic.add(user);
            req.setAttribute("users", logic.findAll());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        req.getRequestDispatcher("/WEB-INF/views/Users.jsp").forward(req, resp);

        //resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
