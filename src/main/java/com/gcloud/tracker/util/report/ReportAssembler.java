package com.gcloud.tracker.util.report;

import com.gcloud.tracker.model.Task;
import com.gcloud.tracker.model.User;
import com.gcloud.tracker.service.TaskService;
import com.gcloud.tracker.service.UserService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReportAssembler {
    private static final Logger log = LoggerFactory.getLogger(ReportAssembler.class);
    UserService userService = new UserService();
    TaskService taskService = new TaskService();

    public void assemblePdf(String pdfPath)  {
        try {
            //Prepare document for assembling
            Document document = new Document();
            //file will be saved to ./src/report.pdf TODO: change file location
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
            document.open();
            BaseFont bf = BaseFont.createFont("./fonts/consola.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bf,14, Font.NORMAL);

            //preparing data required for document
            ArrayList<User> users = new ArrayList<>(userService.findAllUsers());
            ArrayList<Task> tasks;
            Integer count = 1;

            prepareTitle(document, font); //Preparing and adding title to a document

            //assembling the document
            for (User user : users){
                tasks = new ArrayList<>(taskService.getDailyUserTask(user.getId(), LocalDate.now()));
                prepareUserBlock(user, document, font, count);
                document.add(new Paragraph());
                for (Task task : tasks){
                    prepareTaskBlock(task, document, font);
                }
                document.add(new Paragraph("\n"));
                count++;
            }
            document.close();//don't forget to close resources!!
        } catch (FileNotFoundException fe){
            log.error("File Not Found!", fe);
        } catch (DocumentException de){
            log.error("itextpdf library exception!", de);
        } catch (IOException e) {
            log.error("IO Exception occured!", e);
        }
    }

    protected void prepareTitle(Document document, Font font) throws DocumentException {
        Chunk chunk = new Chunk("Отчет за  " + LocalDate.now(), font);
        document.add(chunk);
        document.add(new Paragraph("\n"));
    }

    protected void prepareUserBlock(User user, Document document, Font font, Integer count) throws DocumentException {
        Chunk chunk = new Chunk(
                count.toString() + ") " +
                        user.getFirstName() + " " + user.getLastName(),
                font);
        document.add(chunk);
    }

    protected void prepareTaskBlock(Task task, Document document, Font font) throws DocumentException {
        Chunk chunk = new Chunk(
                task.getDescription() + ": " + task.getHours() +
                        " часов, " + task.getMinutes() + " минут.",
                font);
        document.add(chunk);
        document.add(new Paragraph());
    }
}
