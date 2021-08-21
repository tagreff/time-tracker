package com.gcloud.tracker.util.report;

import com.gcloud.tracker.model.Task;
import com.gcloud.tracker.model.User;
import com.gcloud.tracker.service.TaskService;
import com.gcloud.tracker.service.UserService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReportAssembler {
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
            Chunk chunk = new Chunk();

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
        } catch (FileNotFoundException e){
            e.printStackTrace();
            System.err.println("File Not Found");
        } catch (DocumentException e){
            e.printStackTrace();
            System.err.println("itextpdf library exception");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void prepareTitle(Document document, Font font) throws DocumentException {
        Chunk chunk = new Chunk("Отчет за  " + LocalDate.now().toString(), font);
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
