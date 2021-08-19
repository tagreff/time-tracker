package com.gcloud.tracker.service;

import com.gcloud.tracker.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    public List<User> findAllUsers(){
        //здесь я хочу получить всех юзеров из UserDAO
        return  List.of(new User());
    }
}
