package ru.job4j.dream.servlet;

import ru.job4j.dream.model.PsqlStore;
import ru.job4j.dream.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class AuthServlet.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 30.04.2020
 */
public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user;
        if ("root@local".equals(email) && "root".equals(password)) {
            HttpSession session = req.getSession();
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail(email);
            session.setAttribute("user", admin);
            resp.sendRedirect(String.format("%s/reg.do", req.getContextPath()));
        } else {
            user = PsqlStore.instOf().findByEmail(email);
            if (user == null) {
                req.setAttribute("error", "Не верный email или пароль");
                req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            } else if (email.equals((user.getEmail())) && password.equals(user.getPassword())) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user.getName());
                resp.sendRedirect(String.format("%s/posts.do", req.getContextPath()));
            }
        }
    }
}
