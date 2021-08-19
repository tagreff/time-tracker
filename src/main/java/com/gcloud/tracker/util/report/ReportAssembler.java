package com.gcloud.tracker.util.report;

import com.gcloud.tracker.service.TaskService;
import com.gcloud.tracker.service.UserService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ReportAssembler {
    UserService userService = new UserService();
    TaskService taskService = new TaskService();

    public void assemblePdf()  {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("report.pdf"));

            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk("Hello World", font);

            document.add(chunk);
            document.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
            System.err.println("File Not Found");
        } catch (DocumentException e){
            e.printStackTrace();
            System.err.println("itextpdf library exception");
        }
    }
}
