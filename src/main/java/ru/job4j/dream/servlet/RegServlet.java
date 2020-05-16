package ru.job4j.dream.servlet;

import ru.job4j.dream.model.PsqlStore;
import ru.job4j.dream.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class RegServlet.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 21.04.2020
 */
public class RegServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.setAttribute("users", PsqlStore.instOf().findAllUsers());
        req.getRequestDispatcher("/views/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        PsqlStore.instOf().save(
                new User(Integer.parseInt(
                        req.getParameter("id")),
                        req.getParameter("name"),
                        req.getParameter("email"),
                        req.getParameter("password")));
        resp.sendRedirect(String.format("%s/posts.do", req.getContextPath()));
    }
}
