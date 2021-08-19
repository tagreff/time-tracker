package com.gcloud.tracker.model;

public class User {
    Integer id;

    public User(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    String firstName;
    String lastName;
}
