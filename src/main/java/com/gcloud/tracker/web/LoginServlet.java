package com.gcloud.tracker.web;

import com.gcloud.tracker.dao.UserDAO;
import com.gcloud.tracker.model.User;
import lombok.SneakyThrows;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class LoginServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getRequestDispatcher("/WEb-INF/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {

        userDAO.findByLogin(req.getParameter("login"))
                .ifPresentOrElse(user -> onLoginSuccess(user, req, resp), () -> onLoginFail(resp));

    }
    @SneakyThrows
    private void onLoginSuccess(User user, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("user", user);
        response.sendRedirect("/mainPage");
    }
    @SneakyThrows
    private void onLoginFail(HttpServletResponse resp) {
        resp.sendRedirect("/login");
    }
}

