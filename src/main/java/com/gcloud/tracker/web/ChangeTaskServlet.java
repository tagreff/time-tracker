package com.gcloud.tracker.web;

import com.gcloud.tracker.dao.TaskDAO;
import com.gcloud.tracker.model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/changeTask")
public class ChangeTaskServlet extends HttpServlet {
    TaskDAO taskDAO = new TaskDAO();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Task task = taskDAO.getById(Integer.parseInt(req.getParameter("id")));
        task.setDescription(req.getParameter("description"));
        task.setHours(Integer.parseInt(req.getParameter("hours")));
        task.setMinutes(Integer.parseInt(req.getParameter("minutes")));

        taskDAO.change(task);
        resp.sendRedirect("/mainPage");
    }
}
