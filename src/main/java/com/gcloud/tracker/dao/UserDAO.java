package com.gcloud.tracker.dao;

import com.gcloud.tracker.model.Task;
import com.gcloud.tracker.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {

    public Optional<User> findByLogin(String login){
        User user = new User();
        user.setLogin("User");
        user.setFirstName("Mikhail");
        user.setLastName("Toporov");
        user.setId(1);
        user.setPassword("111");

        return Optional.ofNullable(user);
    }




}
