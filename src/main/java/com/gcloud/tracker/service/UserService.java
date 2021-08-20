package com.gcloud.tracker.service;

import com.gcloud.tracker.dao.UserDAO;
import com.gcloud.tracker.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    public List<User> findAllUsers(){
        //здесь я хочу получить всех юзеров из UserDAO
        UserDAO userDAO = new UserDAO();
        //return userDAO.findAll()
        //      /\
        //      |
        //      |
        return  List.of(new User()); //TODO: имплементируйте метод findALl() в UserDAO!
    }
}
