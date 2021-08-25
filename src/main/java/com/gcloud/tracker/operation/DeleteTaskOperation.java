package com.gcloud.tracker.operation;

import com.gcloud.tracker.dao.TaskDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTaskOperation implements Operation{
    TaskDAO taskDAO = new TaskDAO();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        taskDAO.delete(Integer.parseInt(req.getParameter("taskId")));
        resp.sendRedirect("/mainPage");
    }
}
