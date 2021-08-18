package com.gcloud.tracker.model;

import lombok.Data;

import java.time.LocalDate;

/**
 * Class Task
 *
 * @author Igor Rogachev
 * @version 0.0.1
 * created on 18.08.2021
 */

@Data
public class Task {

    private Integer id;
    private LocalDate date;
    private String description;
    private Integer hours;
    private Integer minutes;

    public Task(LocalDate date, String description, Integer hours, Integer minutes) {
        this.date = date;
        this.description = description;
        this.hours = hours;
        this.minutes = minutes;
    }
}
