package com.gcloud.tracker.web;

import com.gcloud.tracker.dao.TaskDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteTaskServlet")
public class DeleteTaskServlet extends HttpServlet {
    TaskDAO taskDAO = new TaskDAO();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        taskDAO.delete(Integer.parseInt(req.getParameter("taskId")));
    }
}
