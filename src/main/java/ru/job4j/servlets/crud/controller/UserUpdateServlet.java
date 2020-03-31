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

public class UserUpdateServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UserUpdateServlet.class);

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = (String) req.getAttribute("action");
        if (action.equals("edit")) {
            req.getRequestDispatcher("/WEB-INF/views/EditUser.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;
        String action = (String) req.getAttribute("action");
        String name = req.getParameter("name");
        String id = req.getParameter("id");
        try {
            switch (action) {
                case "edit":
                    user = logic.findById(Integer.parseInt(id));
                    user.setName(name);
                    logic.update(user);
                    break;
                case "delete":
                    user = logic.findById(Integer.parseInt(id));
                    logic.delete(user);
                    break;
                default:
                    //
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        try {
            req.setAttribute("users", logic.findAll());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        req.getRequestDispatcher("/WEB-INF/views/Users.jsp").forward(req, resp);
    }
}
