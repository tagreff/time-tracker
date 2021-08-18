package com.gcloud.tracker.web;


import com.gcloud.tracker.dao.TaskDAO;
import com.gcloud.tracker.dao.UserDAO;
import com.gcloud.tracker.model.Task;
import com.gcloud.tracker.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/mainPage")
public class MainServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();
    TaskDAO taskDAO = new TaskDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Task> taskList = new ArrayList<>();
        User user = (User) req.getSession().getAttribute("user");
        taskList = taskDAO.findTaskByUserIdAndDate(user.getId(), LocalDate.now());
        req.setAttribute("tasks", taskList);
        req.setAttribute("date", LocalDate.now());
        req.getRequestDispatcher("/WEB-INF/mainPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
