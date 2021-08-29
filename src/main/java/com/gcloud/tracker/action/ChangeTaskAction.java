package com.gcloud.tracker.action;

import com.gcloud.tracker.dao.TaskDAO;
import com.gcloud.tracker.model.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class ChangeTaskAction implements Action {
    TaskDAO taskDAO = new TaskDAO();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        req.setCharacterEncoding("UTF-8");
        Optional<Task> taskOptional = taskDAO.getById(Integer.parseInt(req.getParameter("id")));
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setDescription(req.getParameter("description"));
            task.setHours(Integer.parseInt(req.getParameter("hours")));
            task.setMinutes(Integer.parseInt(req.getParameter("minutes")));

            taskDAO.edit(task);
        }
        resp.sendRedirect("/mainPage");
    }
}