package com.gcloud.tracker;

import com.gcloud.tracker.dao.TaskDAO;
import com.gcloud.tracker.model.Task;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TaskDAO taskDAO = TaskDAO.getInstance();
        LocalDate date = LocalDate.now();
        List<Task> tasks = taskDAO.findTaskByUserIdAndDate(4, date);
        for (Task t : tasks) {
            System.out.println(t.toString());
        }
    }
}
