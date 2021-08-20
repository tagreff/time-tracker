package com.gcloud.tracker.web;

import com.gcloud.tracker.dao.UserDAO;
import com.gcloud.tracker.model.User;
import com.gcloud.tracker.service.SenderService;
import lombok.SneakyThrows;
import org.w3c.dom.ls.LSOutput;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebServlet("/")
public class LoginServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();
    /* TODO: Enable scheduler */
//    static {
//        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.scheduleAtFixedRate(new SenderService(), 0, 1, TimeUnit.MINUTES);
//    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        Optional<User> user = userDAO.findByLogin(req.getParameter("login"));
        if (user.isPresent()){
            if (user.get().getPassword().equals(req.getParameter("password"))){
                onLoginSuccess(user.get(), req, resp);
            }else {
                onLoginFail(resp, req);
            }
        }else {
            onLoginFail(resp, req);
        }
    }
    @SneakyThrows
    private void onLoginSuccess(User user, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("user", user);
        response.sendRedirect("/mainPage");
    }
    @SneakyThrows
    private void onLoginFail(HttpServletResponse resp, HttpServletRequest req) {

        resp.sendRedirect("/?error&");
    }
}

