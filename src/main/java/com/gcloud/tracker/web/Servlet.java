package com.gcloud.tracker.web;

import com.gcloud.tracker.operation.Operation;
import com.gcloud.tracker.operation.OperationStrategy;
import lombok.SneakyThrows;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/")
public class Servlet extends HttpServlet {

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Operation operation = OperationStrategy.getOperation(request);
        operation.doGet(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Operation operation = OperationStrategy.getOperation(request);
        operation.doPost(request, response);
    }
}
