package com.gcloud.tracker.web;


import com.gcloud.tracker.dao.TaskDAO;
import com.gcloud.tracker.model.Task;
import com.gcloud.tracker.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/mainPage")
public class MainServlet extends HttpServlet {
    private static final TaskDAO taskDAO = new TaskDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        List<Task> taskList;
        User user = (User) req.getSession().getAttribute("user");
        if(user == null){ //redirect unauthorized user to login page
            resp.sendRedirect("/");
        } else {
            taskList = taskDAO.findTaskByUserIdAndDate(user.getId(), LocalDate.now());
            req.setAttribute("tasks", taskList);
            req.setAttribute("date", LocalDate.now());
            req.getRequestDispatcher("/WEB-INF/mainPage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        Task task = new Task();
        task.setDescription(req.getParameter("description"));
        task.setHours(Integer.parseInt(req.getParameter("hours")));
        task.setMinutes(Integer.parseInt(req.getParameter("minutes")));
        task.setDate(LocalDate.now());
        task.setUserId(Integer.parseInt(req.getParameter("id")));
        taskDAO.create(task);
        resp.sendRedirect("/mainPage");
    }
}
