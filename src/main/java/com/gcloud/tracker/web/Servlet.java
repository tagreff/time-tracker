package com.gcloud.tracker.web;

import com.gcloud.tracker.action.Action;
import com.gcloud.tracker.action.ActionFactory;
import lombok.SneakyThrows;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class Servlet extends HttpServlet {

    @SneakyThrows
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Action action = ActionFactory.getAction(req);
        action.doGet(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Action action = ActionFactory.getAction(req);
        action.doPost(req, resp);
    }
}