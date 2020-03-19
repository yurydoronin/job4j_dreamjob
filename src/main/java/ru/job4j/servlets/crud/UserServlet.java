package ru.job4j.servlets.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class UserServlet.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 18.03.2020
 */
public class UserServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        if (action.equals("findAll")) {
            writer.append(logic.findAll().toString());
        } else if (action.equals("findById")) {
            writer.append(logic.findById(Integer.parseInt(id)).toString());
        }
        writer.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        User user;
        String action = req.getParameter("action");
        String name = req.getParameter("name");
        String id = req.getParameter("id");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        switch (action) {
            case "add":
                user = new User(Integer.parseInt(id), name);
                logic.add(user);
                break;
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
        writer.flush();
    }
}
