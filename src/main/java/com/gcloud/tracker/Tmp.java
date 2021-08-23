package com.gcloud.tracker;

import com.gcloud.tracker.dao.UserDAO;
import com.gcloud.tracker.model.User;
import com.gcloud.tracker.util.email.EmailUtil;
import com.gcloud.tracker.util.report.ReportAssembler;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Oleksandr Storozhuk
 * @version 0.0.1
 * created on 20.08.2021
 */
public class Tmp {
    public static void main(String[] args) {
//        String fileName = System.getProperty("user.dir")
//                .concat(File.separator)
//                .concat(LocalDate.now().toString().concat("_report.pdf"));
//        new ReportAssembler().assemblePdf(fileName);
//        EmailUtil.sendMail(fileName);
        System.out.println(LocalDateTime.now().getHour()*60 + LocalDateTime.now().getMinute());
    }
}
