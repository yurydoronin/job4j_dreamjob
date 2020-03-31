package ru.job4j.servlets.crud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.servlets.crud.model.Validate;
import ru.job4j.servlets.crud.model.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Class UserServlet.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 18.03.2020
 */
public class UsersServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UsersServlet.class);

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("users", logic.findAll());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        req.getRequestDispatcher("/WEB-INF/views/Users.jsp").forward(req, resp);
    }
}
