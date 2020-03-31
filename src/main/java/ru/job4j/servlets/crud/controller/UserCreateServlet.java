package ru.job4j.servlets.crud.controller;

import ru.job4j.servlets.crud.User;
import ru.job4j.servlets.crud.model.Validate;
import ru.job4j.servlets.crud.model.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserCreateServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("create")) {
            req.getRequestDispatcher("/views/AddUser.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;
        String name = req.getParameter("name");
        String id = req.getParameter("id");
        user = new User(Integer.parseInt(id), name);
        logic.add(user);
        resp.sendRedirect(String.format("%s/AddUser.jsp", req.getContextPath()));
    }
}
