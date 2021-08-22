package com.gcloud.tracker.web;

import com.gcloud.tracker.dao.TaskDAO;
import com.gcloud.tracker.model.Task;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/changeTask")
public class ChangeTaskServlet extends HttpServlet {
    TaskDAO taskDAO = new TaskDAO();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        req.setCharacterEncoding("UTF-8");
        Optional<Task> taskOptional = taskDAO.getById(Integer.parseInt(req.getParameter("id")));
        if (taskOptional.isPresent())
        {
            Task task = taskOptional.get();
            task.setDescription(req.getParameter("description"));
            task.setHours(Integer.parseInt(req.getParameter("hours")));
            task.setMinutes(Integer.parseInt(req.getParameter("minutes")));

            taskDAO.edit(task);
        }
        resp.sendRedirect("/mainPage");
    }
}
