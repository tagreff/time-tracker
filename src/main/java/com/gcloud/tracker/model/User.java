package com.gcloud.tracker.model;

import lombok.Data;

@Data
public class User {
    Integer id;
    String firstName;
    String lastName;
    String login;
    String password;
}
