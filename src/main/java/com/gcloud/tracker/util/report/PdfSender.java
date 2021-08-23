package com.gcloud.tracker.util.report;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import com.gcloud.tracker.util.email.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PdfSender {
    private static final Logger log = LoggerFactory.getLogger(PdfSender.class);
    public static boolean sendPdf(){
        try {
            String fileName;
            fileName = getPath();
            createPdf(fileName);
            EmailUtil.sendMail(fileName);
            log.info(String.format("Report %s sent!", fileName));
            return true;
        } catch (IOException e){
            log.error("Report doesn't sent!", e);
            return false;
        }
    }

    public static String getPath() throws IOException {
        String baseDirectory;
        if(System.getProperty("catalina.base") != null) {
            baseDirectory = System.getProperty("catalina.base");
        } else {
            baseDirectory = System.getProperty("user.dir");
        }
        String pathName = baseDirectory
                .concat(File.separator)
                .concat("PDF-reports")
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
