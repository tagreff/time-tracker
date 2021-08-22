package com.gcloud.tracker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gcloud.tracker.util.report.PdfSender;


/**
 * @author Oleksandr Storozhuk
 * @version 0.0.1
 * created on 20.08.2021
 */
public class SenderService implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(SenderService.class);

    @Override
    public void run() {
        Boolean isSent = PdfSender.sendPdf();
        if(isSent)
            log.info("Report sent successfully.");
        else log.warn("Report wasn't send");
    }
}
