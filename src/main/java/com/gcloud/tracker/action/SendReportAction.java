package com.gcloud.tracker.action;

import com.gcloud.tracker.util.report.PdfSender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendReportAction implements Action {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PdfSender.sendPdf();
        resp.sendRedirect("/?report&");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }
}