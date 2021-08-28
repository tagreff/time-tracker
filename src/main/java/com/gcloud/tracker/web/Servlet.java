package com.gcloud.tracker.web;

import com.gcloud.tracker.action.*;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class Servlet extends HttpServlet {

    private Map<String, Action> actions = new HashMap<>();

    @Override
    public void init() throws ServletException {
        actions.put("/changeTask", new ChangeTaskAction());
        actions.put("/createUser", new CreateUserAction());
        actions.put("/deleteTask", new DeleteTaskAction());
        actions.put("/", new LoginAction());
        actions.put("/mainPage", new MainAction());
        actions.put("/sendReport", new SendReportAction());
    }

    @SneakyThrows
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Action action = actions.get(req.getServletPath());
        action.doGet(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Action action = actions.get(req.getServletPath());
        action.doPost(req, resp);
    }
}