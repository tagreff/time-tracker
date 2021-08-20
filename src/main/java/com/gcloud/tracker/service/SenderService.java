package com.gcloud.tracker.service;

import com.gcloud.tracker.util.email.EmailUtil;
import com.gcloud.tracker.util.report.ReportAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.LocalDate;

/**
 * @author Oleksandr Storozhuk
 * @version 0.0.1
 * created on 20.08.2021
 */
public class SenderService implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(SenderService.class);

    @Override
    public void run() {
        String pathName = System.getProperty("user.dir")
                .concat(File.separator)
                .concat("reports")
                .concat(File.separator);
        File file = new File(pathName);
        if(!file.exists()) {
            if(!file.mkdir()) {
                log.error(String.format("Can not create directory %s!", file.getAbsolutePath()));
                return;
            }
        } else if(file.isFile()){
            log.error(String.format("File %s is not directory!", file.getAbsolutePath()));
            return;
        }
        String fileName = pathName.concat(LocalDate.now().toString().concat("_report.pdf"));
        new ReportAssembler().assemblePdf(fileName);
        EmailUtil.sendMail(fileName);
        log.info(String.format("Report %s sent.", fileName));
    }
}
