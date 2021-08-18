package com.gcloud.tracker.model;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    Integer id;
    String firstName;
    String lastName;
    String login;
    String password;
    Integer roleID;
}