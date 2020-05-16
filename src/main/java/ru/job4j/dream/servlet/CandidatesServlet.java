package ru.job4j.dream.servlet;

import ru.job4j.dream.model.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class CandidatesServlet.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 24.04.2020
 */
public class CandidatesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("candidates", PsqlStore.instOf().findAllCandidates());
        req.getRequestDispatcher("/views/candidates.jsp").forward(req, resp);
    }
}