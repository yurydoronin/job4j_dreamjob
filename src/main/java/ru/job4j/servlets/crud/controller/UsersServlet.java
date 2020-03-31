package ru.job4j.servlets.crud.controller;

import ru.job4j.servlets.crud.User;
import ru.job4j.servlets.crud.model.Validate;
import ru.job4j.servlets.crud.model.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class UserServlet.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 18.03.2020
 */
public class UsersServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        if (action.equals("findAll")) {
            StringBuilder sb = new StringBuilder("<table>");
            for (User user : logic.findAll()) {
                sb.append("<tr><td>" + user.getName() + "</td></tr>");
            }
            sb.append("</table>");
            //req.setAttribute("...", sb.toString());
        } else if (action.equals("findById")) {
            User user = logic.findById(Integer.parseInt(id));
            //req.setAttribute("...", user);
        }
        req.getRequestDispatcher("/views/Users.jsp").forward(req, resp);
    }
}
