package ru.job4j.servlets.crud.controller;

import ru.job4j.servlets.crud.User;
import ru.job4j.servlets.crud.model.Validate;
import ru.job4j.servlets.crud.model.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserUpdateServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;
        String action = req.getParameter("action");
        String name = req.getParameter("name");
        String id = req.getParameter("id");
        switch (action) {
            case "update":
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
        req.getRequestDispatcher("/views/EditUser.jsp").forward(req, resp);
    }
}
