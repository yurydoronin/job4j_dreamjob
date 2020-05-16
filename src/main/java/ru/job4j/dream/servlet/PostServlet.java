package ru.job4j.dream.servlet;

import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class PostServlet.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 21.04.2020
 */
public class PostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.setAttribute("posts", PsqlStore.instOf().findAllPosts());
        req.getRequestDispatcher("/views/posts.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        PsqlStore.instOf().save(
                new Post(Integer.parseInt(req.getParameter("id")),
                        req.getParameter("name"),
                        "text"));
        resp.sendRedirect(String.format("%s/posts.do", req.getContextPath()));
    }
}
