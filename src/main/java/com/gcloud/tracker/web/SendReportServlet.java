package com.gcloud.tracker.web;

import com.gcloud.tracker.util.report.PdfSender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sendReport")
public class SendReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PdfSender.sendPdf();
        resp.sendRedirect("/?report&");
    }
}
