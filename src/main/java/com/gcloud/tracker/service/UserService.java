package com.gcloud.tracker.service;

import com.gcloud.tracker.dao.UserDAO;
import com.gcloud.tracker.model.User;

import java.util.List;

public class UserService {
    //private final UserDAO userDAO = new UserDAO();
    UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> findAllUsers(){
        return userDAO.findAll();
    }
}
