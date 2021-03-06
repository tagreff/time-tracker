package com.gcloud.tracker.service;

import com.gcloud.tracker.dao.TaskDAO;
import com.gcloud.tracker.model.Task;

import java.time.LocalDate;
import java.util.List;

public class TaskService {

    private final TaskDAO taskDAO = new TaskDAO();
    public List<Task> getDailyUserTask(Integer id, LocalDate today) {
        return taskDAO.findTaskByUserIdAndDate(id, today);
    }
}
