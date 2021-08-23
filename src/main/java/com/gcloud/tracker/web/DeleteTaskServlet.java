package com.gcloud.tracker.web;

import com.gcloud.tracker.dao.TaskDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteTask")
public class DeleteTaskServlet extends HttpServlet {
    TaskDAO taskDAO = new TaskDAO();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        taskDAO.delete(Integer.parseInt(req.getParameter("taskId")));
        resp.sendRedirect("/mainPage");
    }
}
