package com.gcloud.tracker.dao;

import com.gcloud.tracker.model.Task;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskDAO {

    public void delete (Integer taskId){

    }
    public void change (Task task){

    }
    public Task getById(Integer id){
        return new Task();
    }

    public List<Task> findTaskByUserIdAndDate(Integer id, LocalDate date){

        Task task = new Task();
        task.setId(1);
        task.setUserId(1);
        task.setDescription("123");
        task.setDate(LocalDate.now());
        task.setHours(2);
        task.setMinutes(3);
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        return taskList;
    }
}
