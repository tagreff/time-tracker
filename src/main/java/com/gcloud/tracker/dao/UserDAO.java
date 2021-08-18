package com.gcloud.tracker.dao;

import com.gcloud.tracker.model.User;

import java.util.Optional;

public class UserDAO {

    public Optional<User> findByLogin(String login){
        return Optional.ofNullable(new User());

    }
}
