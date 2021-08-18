package com.gcloud.tracker.dao;

import com.gcloud.tracker.model.Task;
import com.gcloud.tracker.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {

    public Optional<User> findByLogin(String login){
        return Optional.ofNullable(new User());
    }

    public List<Task> findTaskByUserIdAndDate(Integer id, LocalDate date){
        return new ArrayList<>();
    }
}
