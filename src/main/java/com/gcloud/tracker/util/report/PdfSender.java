package com.gcloud.tracker.util.report;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import com.gcloud.tracker.service.SenderService;
import com.gcloud.tracker.util.email.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PdfSender {
    private static final Logger log = LoggerFactory.getLogger(SenderService.class);
    public static boolean sendPdf(){
        try {
            String fileName;
            fileName = getPath();
            createPdf(fileName);
            EmailUtil.sendMail(fileName);
            return true;
        } catch (IOException e){
           log.error("Error occured when trying to send file", e);
            return false;
        }
    }

    public static String getPath() throws IOException {
        String pathName = System.getProperty("user.dir")
                .concat(File.separator)
                .concat("reports")
                .concat(File.separator);
        File file = new File(pathName);
        if(!file.exists()) {
            if(!file.mkdir()) {
                log.error(String.format("Can not create directory %s!", file.getAbsolutePath()));
                throw new IOException();
            }
        } else if(file.isFile()){
            log.error(String.format("File %s is not directory!", file.getAbsolutePath()));
            throw new IOException();
        }
        return pathName.concat(LocalDate.now().toString().concat("_report.pdf"));
    }

    public static void createPdf(String fileName){
        new ReportAssembler().assemblePdf(fileName);
    }

}
