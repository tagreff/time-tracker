package com.gcloud.tracker.service;

import com.gcloud.tracker.dao.UserDAO;
import com.gcloud.tracker.model.User;

import java.util.List;

public class UserService {
    private final UserDAO userDAO = new UserDAO();
    public List<User> findAllUsers(){
        return userDAO.findAll();
    }
}
